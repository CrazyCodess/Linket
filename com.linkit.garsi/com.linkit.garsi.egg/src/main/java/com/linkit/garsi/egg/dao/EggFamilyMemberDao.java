package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggFamilyMember;

/**
 * 家庭历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggFamilyMemberDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggFamilyMember familyMember)
	{
		this.hibernateTemplate.save(familyMember);

	}

	/**
	 * 删除
	 * 
	 * @param familyMemberId
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyMember(String familyMemberId) throws DataValidateException
	{
		deleteEggFamilyHistoryByAttr(familyMemberId, null,null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyHistoryByAttr(String familyMemberId,String familyHistoryId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(familyHistoryId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggFamilyMember t where 1=1");
		if (StringUtil.isNotBlank(familyMemberId))
		{
			params.add(familyMemberId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(familyHistoryId))
		{
			params.add(familyHistoryId);
			hql.append(" and t.familyHistoryId=?");
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
	public EggFamilyMember[] getAllEggFamilyHistoryByAttr(String id, String familyHistoryId,String resourceId)
	{

		StringBuffer hql = new StringBuffer("from EggFamilyMember t where 1=1 ");
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
		
		if (StringUtil.isNotBlank(familyHistoryId))
		{
			params.add(familyHistoryId);
			hql.append(" and t.familyHistoryId=?");
		}
		hql.append(" order by t.sortNum");
		return this.hibernateTemplate.queryForArray(hql.toString(), params.toArray(), EggFamilyMember.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggFamilyMember(EggFamilyMember eggFamilyMember)
	{
		this.hibernateTemplate.update(eggFamilyMember);
	}

}
