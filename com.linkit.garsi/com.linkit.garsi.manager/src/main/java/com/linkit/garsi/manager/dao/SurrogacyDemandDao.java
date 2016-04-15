package com.linkit.garsi.manager.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.manager.vo.SurrogacyDemand;

@Repository
public class SurrogacyDemandDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public SurrogacyDemand getSurrogacyDemand(String id)
	{
		return this.hibernateTemplate.queryForObject("from SurrogacyDemand t where t.id=?", new Object[] { id }, SurrogacyDemand.class);
	}

	public SurrogacyDemand getSurrogacyDemandByUserId(String userId)
	{
		return this.hibernateTemplate.queryForObject("from SurrogacyDemand t where t.userId=?", new Object[] { userId }, SurrogacyDemand.class);
	}

	public void updateResourceId(String userId, String resourceId)
	{
		this.hibernateTemplate.executeUpdate("update SurrogacyDemand t set t.resourceId=? where t.userId=?", new Object[] { resourceId, userId });
	}

	public void update(SurrogacyDemand demand)
	{
		this.hibernateTemplate.update(demand);
	}

	public void deleteByUserId(String userId)
	{
		this.hibernateTemplate.executeUpdate("delete from SurrogacyDemand t where t.userId=?", new Object[] { userId });
	}

	public void delete(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from SurrogacyDemand t where t.id=?", new Object[] { id });
	}

	public void insert(SurrogacyDemand demand)
	{
		this.hibernateTemplate.save(demand);
	}
}
