package com.linkit.garsi.manager.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.manager.vo.SpermDemand;

@Repository
public class SpermDemandDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public SpermDemand getSpermDemand(String id)
	{
		return this.hibernateTemplate.queryForObject("from SpermDemand t where t.id=?", new Object[] { id }, SpermDemand.class);
	}

	public SpermDemand getSpermDemandByUserId(String userId)
	{
		return this.hibernateTemplate.queryForObject("from SpermDemand t where t.userId=?", new Object[] { userId }, SpermDemand.class);
	}

	public void updateResourceId(String userId, String resourceId)
	{
		this.hibernateTemplate.executeUpdate("update SpermDemand t set t.resourceId=? where t.userId=?", new Object[] { resourceId, userId });
	}

	public void update(SpermDemand demand)
	{
		this.hibernateTemplate.update(demand);
	}

	public void deleteByUserId(String userId)
	{
		this.hibernateTemplate.executeUpdate("delete from SpermDemand t where t.userId=?", new Object[] { userId });
	}

	public void delete(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from SpermDemand t where t.id=?", new Object[] { id });
	}

	public void insert(SpermDemand demand)
	{
		this.hibernateTemplate.save(demand);
	}
}
