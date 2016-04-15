package com.linkit.garsi.manager.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.manager.vo.EggDemand;

/**
 * 为顾客信息服务的DAO
 * 
 * @author wang.sheng
 * 
 */
@Repository
public class EggDemandDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public EggDemand getEggDemand(String id)
	{
		return this.hibernateTemplate.queryForObject("from EggDemand t where t.id=?", new Object[] { id }, EggDemand.class);
	}

	public EggDemand getEggDemandByUserId(String userId)
	{
		return this.hibernateTemplate.queryForObject("from EggDemand t where t.userId=?", new Object[] { userId }, EggDemand.class);
	}

	public void updateResourceId(String userId, String resourceId)
	{
		this.hibernateTemplate.executeUpdate("update EggDemand t set t.resourceId=? where t.userId=?", new Object[] { resourceId, userId });
	}

	public void update(EggDemand demand)
	{
		this.hibernateTemplate.update(demand);
	}

	public void deleteByUserId(String userId)
	{
		this.hibernateTemplate.executeUpdate("delete from EggDemand t where t.userId=?", new Object[] { userId });
	}

	public void delete(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from EggDemand t where t.id=?", new Object[] { id });
	}

	public void insert(EggDemand demand)
	{
		this.hibernateTemplate.save(demand);
	}
}
