package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggHealHistory;

/**
 * 健康历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggHealHistoryDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggHealHistory healHistor)
	{
		this.hibernateTemplate.save(healHistor);

	}

	/**
	 * 删除
	 * 
	 * @param HealHistorId
	 * @throws DataValidateException
	 */
	public void deleteEggHealHistory(String healHistoryId) throws DataValidateException
	{
		deleteEggHealHistoryByAttr(healHistoryId, null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggHealHistoryByAttr(String healHistoryId, String eggId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(eggId) && StringUtil.isBlank(healHistoryId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggHealHistory t where 1=1");
		if (StringUtil.isNotBlank(healHistoryId))
		{
			params.add(healHistoryId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(eggId))
		{
			params.add(eggId);
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
	public EggHealHistory getEggHealHistoryById(String healHistoryId)
	{
		return this.hibernateTemplate.queryForObject("from EggHealHistory t where t.id =?", new Object[] { healHistoryId }, EggHealHistory.class);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggHealHistory[] getAllEggHealHistory()
	{
		return this.hibernateTemplate.queryForArray("from EggHealHistory t order by t.updateTime desc", null, EggHealHistory.class);
	}

	/**
	 * 获取egg下所有记录
	 * 
	 * @return
	 */
	public EggHealHistory[] getAllEggHealHistoryByResourceId(String resourceId)
	{
		return this.hibernateTemplate.queryForArray("from EggHealHistory t where t.resourceId=? order by t.updateTime desc", new Object[] { resourceId },
				EggHealHistory.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggHealHistory(EggHealHistory healHistory)
	{
		this.hibernateTemplate.update(healHistory);
	}

}
