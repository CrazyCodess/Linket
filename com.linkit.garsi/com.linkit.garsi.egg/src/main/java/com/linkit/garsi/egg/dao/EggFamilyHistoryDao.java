package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggFamilyHistory;
import com.linkit.garsi.egg.vo.EggFamilyMember;

/**
 * 家庭历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggFamilyHistoryDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	@Resource
	private EggFamilyMemberDao memberDao;

	public void insert(EggFamilyHistory familyHistory)
	{
		this.hibernateTemplate.save(familyHistory);

	}

	/**
	 * 删除
	 * 
	 * @param FamilyHistoryId
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyHistory(String familyHistoryId) throws DataValidateException
	{
		deleteEggFamilyHistoryByAttr(familyHistoryId, null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyHistoryByAttr(String familyHistoryId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(familyHistoryId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggFamilyHistory t where 1=1");
		if (StringUtil.isNotBlank(familyHistoryId))
		{
			params.add(familyHistoryId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(resourceId))
		{
			params.add(resourceId);
			hql.append(" and t.resourceId=?");
		}
		
		this.hibernateTemplate.executeUpdate(hql.toString(), params.toArray());
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggFamilyHistory[] getAllEggFamilyHistoryByAttr(String id, String resourceId)
	{

		StringBuffer hql = new StringBuffer("from EggFamilyHistory t where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(id))
		{
			hql.append(" and t.id = ?");
			params.add(id);
		}

		if (StringUtils.isNotEmpty(resourceId))
		{
			hql.append(" and t.resourceId = ?");
			params.add(resourceId);
		}
		hql.append(" order by t.updateTime desc");
		EggFamilyHistory[] array = this.hibernateTemplate.queryForArray(hql.toString(), params.toArray(), EggFamilyHistory.class);
		if(array != null && array.length>0){
			for(EggFamilyHistory family : array){
				EggFamilyMember[] members =memberDao.getAllEggFamilyHistoryByAttr(null, family.getId(), null);
				if(members!=null && members.length>0){
					family.setFamilyMembers(Arrays.asList(members));
				}else{
					family.setFamilyMembers(null);
				}
			}
		}
		return array;
	}

	/**
	 * 获取分页查询
	 * 
	 * @param resourceType
	 * @param creatorId
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<EggFamilyHistory> getPagingEggFamilyHistoryByAttr(EggFamilyHistory familyHistory, int start, int limit)
	{
		StringBuffer hql = new StringBuffer("from EggFamilyHistory t where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(familyHistory.getResourceId()))
		{
			hql.append(" and t.resourceId = ?");
			params.add(familyHistory.getResourceId());
		}

		if (StringUtils.isNotEmpty(familyHistory.getId()))
		{
			hql.append(" and t.id = ?");
			params.add(familyHistory.getId());
		}
		long totalCount = this.hibernateTemplate.getTotalCount(hql.toString(), params.toArray());
		EggFamilyHistory[] resources = this.hibernateTemplate.queryForArray(hql.toString(), start, limit, params.toArray(), EggFamilyHistory.class);
		
		if(resources != null && resources.length>0){
			for(EggFamilyHistory family : resources){
				EggFamilyMember[] members =memberDao.getAllEggFamilyHistoryByAttr(null, family.getId(), null);
				if(members!=null && members.length>0){
					family.setFamilyMembers(Arrays.asList(members));
				}
			}
		}
		return new PagingResult<EggFamilyHistory>(totalCount, resources);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggFamilyHistory(EggFamilyHistory familyHistory)
	{
		this.hibernateTemplate.update(familyHistory);
	}

}
