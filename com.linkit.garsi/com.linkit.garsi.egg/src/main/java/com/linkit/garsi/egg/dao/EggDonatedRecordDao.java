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
import com.linkit.garsi.egg.vo.EggDonatedRecord;

/**
 * 捐赠历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggDonatedRecordDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggDonatedRecord donatedRecord)
	{
		this.hibernateTemplate.save(donatedRecord);

	}

	/**
	 * 删除
	 * 
	 * @param familyMemberId
	 * @throws DataValidateException
	 */
	public void deleteEggDonatedRecord(String donatedRecordId) throws DataValidateException
	{
		deleteEggDonatedRecordByAttr(donatedRecordId,null,null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggDonatedRecordByAttr(String donatedRecordId,String askId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(donatedRecordId) && StringUtil.isBlank(askId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggDonatedRecord t where 1=1");
		if (StringUtil.isNotBlank(donatedRecordId))
		{
			params.add(donatedRecordId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(askId))
		{
			params.add(askId);
			hql.append(" and t.askId=?");
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
	public EggDonatedRecord[] getAllEggDonatedRecordByAttr(String id,String askId, String resourceId)
	{

		StringBuffer hql = new StringBuffer("from EggDonatedRecord t where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(id))
		{
			hql.append(" and t.id = ?");
			params.add(id);
		}
		
		if (StringUtils.isNotEmpty(askId))
		{
			hql.append(" and t.askId = ?");
			params.add(askId);
		}
		
		if (StringUtils.isNotEmpty(resourceId))
		{
			hql.append(" and t.resourceId = ?");
			params.add(resourceId);
		}
		
		return this.hibernateTemplate.queryForArray(hql.toString(), params.toArray(), EggDonatedRecord.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggDonatedRecord(EggDonatedRecord eggDonatedRecord)
	{
		this.hibernateTemplate.update(eggDonatedRecord);
	}

}
