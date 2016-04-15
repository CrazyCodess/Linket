package com.linkit.garsi.common.resource.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.eclipse.jetty.util.StringUtil;
import org.hibernate.Session;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.resource.vo.GResource;

/**
 * 资源DAO
 * 
 * @author wang.sheng
 * 
 */
@Repository
public class ResourceDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(GResource resource)
	{
		this.hibernateTemplate.save(resource);
	}

	public void update(GResource resource)
	{
		this.hibernateTemplate.update(resource);
	}

	public void delete(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from GResource t where t.id=?", new Object[] { id });
	}

	public void deleteByDetailId(String detailId)
	{
		this.hibernateTemplate.executeUpdate("delete from GResource t where t.detailId=?", new Object[] { detailId });
	}

	public GResource getResource(String id)
	{
		return this.hibernateTemplate.queryForObject("from GResource t where t.id=?", new Object[] { id }, GResource.class);
	}

	public void updateProcessState(String resourceId, String processState)
	{
		this.hibernateTemplate.executeUpdate("update GResource t set t.processState=? where t.id=?", new Object[] { processState, resourceId });
	}

	public void updateProcessStateByDetailId(String resourceId, String processState)
	{
		this.hibernateTemplate.executeUpdate("update GResource t set t.processState=? where t.detailId=?", new Object[] { processState, resourceId });
	}

	/**
	 * 将一组资源更新流程状态
	 * 
	 * @param resourceIds
	 * @param processState
	 */
	public void updateProcessState(String[] resourceIds, String processState)
	{
		String hql = "update GResource t set t.processState=:processState where t.id in(:resourceIds)";
		Session session = this.hibernateTemplate.getCurrentSession();
		session.createQuery(hql).setParameter("processState", processState).setParameterList("resourceIds", resourceIds).executeUpdate();
	}

	/**
	 * 更新使能状态
	 * 
	 * @param resourceIds
	 * @param resourceState
	 */
	public void updateResourceState(String resourceId, String resourceState)
	{
		String hql = "update GResource t set t.resourceState=:resourceState where t.id =:resourceId";
		Session session = this.hibernateTemplate.getCurrentSession();
		session.createQuery(hql).setParameter("resourceState", resourceState).setParameter("resourceId", resourceId).executeUpdate();
	}

	/**
	 * 通过DetailId更新使能状态
	 * 
	 * @param resourceId
	 * @param resourceState
	 */
	public void updateResourceStateByDetailId(String resourceId, String resourceState)
	{
		String hql = "update GResource t set t.resourceState=:resourceState where t.detailId =:resourceId";
		Session session = this.hibernateTemplate.getCurrentSession();
		session.createQuery(hql).setParameter("resourceState", resourceState).setParameter("resourceId", resourceId).executeUpdate();
	}

	/**
	 * 选择一个资源改变状态
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resourceState
	 */
	public void selectResource(String userId, String resourceId, String resourceState, String processState)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("update GResource t set ");

		if (StringUtil.isNotBlank(resourceId))
		{
			params.add(resourceId);
			hql.append(" t.detailId=?");
		}

		if (StringUtil.isNotBlank(resourceState))
		{
			params.add(resourceState);
			hql.append(",t.resourceState=?");
		}
		if (StringUtil.isNotBlank(userId))
		{
			params.add(userId);
			hql.append(",t.customerId=?");
		}

		if (StringUtil.isNotBlank(processState))
		{
			params.add(processState);
			hql.append(",t.processState=?");
		}
		hql.append(" where t.detailId=?");
		params.add(resourceId);

		this.hibernateTemplate.executeUpdate(hql.toString(), params.toArray());
	}

	/**
	 * 获取创建者所辖的GResource集合
	 * 
	 * @param userId
	 * @return
	 */
	public GResource[] getResourcesByCreator(String userId)
	{
		String hql = "from GResource t where t.creatorId=? order by t.createTime desc";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { userId }, GResource.class);
	}

	public GResource[] getResources(String resourceType, String[] ids)
	{
		String hql = "from GResource t where t.resourceType=:resourceType and t.id in(:ids) order by t.title";
		Session session = this.hibernateTemplate.getCurrentSession();
		List<?> list = session.createQuery(hql).setParameter("resourceType", resourceType).setParameterList("ids", ids).list();
		return list.toArray(new GResource[0]);
	}

	/**
	 * 获取被顾客锁定的GResource集合
	 * 
	 * @param userId
	 * @return
	 */
	public GResource[] getResourcesByCustomer(String resourceType, String userId)
	{
		String hql = "from GResource t where t.resourceType=? and t.customerId=? order by t.title";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { resourceType, userId }, GResource.class);
	}

	public GResource[] getResourcesByCustomer(String userId)
	{
		String hql = "from GResource t where t.customerId=? order by t.resourceType,t.title";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { userId }, GResource.class);
	}

	public Set<String> getFinallyResourceIdSetByCustomer(String userId)
	{
		String hql = "select t.id from GResource t where t.customerId=? and t.processState=?";
		String[] resourceIds = this.hibernateTemplate.queryForArray(hql, new Object[] { userId, ProcessState.FINALLY }, String.class);
		return new HashSet<String>(Arrays.asList(resourceIds));
	}

	public Set<String> getResourceIdSetByCustomer(String resourceType, String userId)
	{
		String hql = "select t.id from GResource t where t.resourceType=? and t.customerId=? order by t.title";
		String[] resourceIds = this.hibernateTemplate.queryForArray(hql, new Object[] { resourceType, userId }, String.class);
		return new HashSet<String>(Arrays.asList(resourceIds));
	}

	/**
	 * 获取分页查询的空闲资源集合
	 * 
	 * @param resourceType
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> getPagingFreeResources(String resourceType, int start, int limit)
	{
		String hql = "from GResource t where t.processState=? and t.resourceType=? order by t.title";
		Object[] params = new Object[] { ProcessState.FREE, resourceType };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = this.hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}

	/**
	 * 获取分页查询的空闲可用资源集合
	 * 
	 * @param resourceType
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> getPagingFreeEnableResources(String resourceType, int start, int limit)
	{
		String hql = "from GResource t where t.processState=? and t.resourceState=? and t.resourceType=? order by t.title";
		Object[] params = new Object[] { ProcessState.FREE, ResourceState.ENABLE, resourceType };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = this.hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}

	/**
	 * 获取所有空闲的资源
	 * 
	 * @param resourceType
	 * @return
	 */
	public GResource[] getAllFreeResources(String resourceType)
	{
		String hql = "from GResource t where t.processState=? and t.resourceType=? order by t.title";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { ProcessState.FREE, resourceType }, GResource.class);
	}

	/**
	 * 获取分页查询的资源集合
	 * 
	 * @param resourceType
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> getPagingResources(String resourceType, int start, int limit)
	{
		String hql = "from GResource t where t.resourceType=? order by t.title";
		Object[] params = new Object[] { resourceType };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = this.hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}

	/**
	 * 获取分页查询的资源集合
	 * 
	 * @param resourceType
	 * @param creatorId
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> getPagingResourcesByCreator(String resourceType, String creatorId, int start, int limit)
	{
		String hql = "from GResource t where t.resourceType=? and t.creatorId=? order by t.title";
		Object[] params = new Object[] { resourceType, creatorId };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = this.hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}

	/**
	 * 获取所有的资源
	 * 
	 * @param resourceType
	 * @return
	 */
	public GResource[] getAllResources(String resourceType)
	{
		String hql = "from GResource t where t.resourceType=? order by t.title";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { resourceType }, GResource.class);
	}
}
