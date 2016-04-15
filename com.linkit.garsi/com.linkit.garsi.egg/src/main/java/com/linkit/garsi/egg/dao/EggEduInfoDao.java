package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggEduInfo;

@Repository
public class EggEduInfoDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggEduInfo eduInfo)
	{
		this.hibernateTemplate.save(eduInfo);

	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEduInfo(String eduInfoId) throws DataValidateException
	{
		deleteEduInfoByAttr(eduInfoId, null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEduInfoByAttr(String eduInfoId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(eduInfoId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggEduInfo t where 1=1");
		if (StringUtil.isNotBlank(eduInfoId))
		{
			params.add(eduInfoId);
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
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggEduInfo getEduInfoById(String eduInfoId)
	{
		return this.hibernateTemplate.queryForObject("from EggEduInfo t where t.id =?", new Object[] { eduInfoId }, EggEduInfo.class);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggEduInfo[] getAllEduInfo()
	{
		return this.hibernateTemplate.queryForArray("from EggEduInfo t order by t.updateTime desc", null, EggEduInfo.class);
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggEduInfo[] getAllEduInfoByEgg(String eggId)
	{
		return this.hibernateTemplate.queryForArray("from EggEduInfo t where t.resourceId = ? order by t.id desc", new Object[] { eggId }, EggEduInfo.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEduInfo(EggEduInfo eduInfo)
	{
		this.hibernateTemplate.update(eduInfo);
	}

}
