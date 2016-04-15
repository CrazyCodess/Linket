package com.linkit.garsi.surrogacy.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.dao.query.QueryFeature;
import org.polaris.framework.common.dao.query.QueryFeatureService;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.ResourceType;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.surrogacy.vo.SurrogacyInfo;
import com.linkit.garsi.surrogacy.vo.SurrogacySearchForm;

@Repository
public class SurrogacyInfoDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private QueryFeatureService queryFeatureService;

	Log log = LogFactory.getLog(getClass());

	public void insert(SurrogacyInfo surrogacy)
	{
		hibernateTemplate.save(surrogacy);
	}

	public SurrogacyInfo getSurrogacyByResourceId(String resourceId)
	{
		return this.hibernateTemplate.queryForObject("from SurrogacyInfo s where s.id =? ", new Object[] { resourceId }, SurrogacyInfo.class);
	}

	public void update(SurrogacyInfo surrogacy)
	{
		hibernateTemplate.update(surrogacy);
	}

	public void deleteByResourceId(String resourceId)
	{
		this.hibernateTemplate.executeUpdate("delete from SurrogacyInfo s where s.id =? ", new Object[] { resourceId });
	}

	public SurrogacyInfo[] getSurrogacys()
	{
		return this.hibernateTemplate.queryForArray("from SurrogacyInfo s", null, SurrogacyInfo.class);
	}

	public PagingResult<Object> getPagingSurrogacysByQueryForm(SurrogacySearchForm searchForm, int start, int limit)
	{
		String hql = "from SurrogacyInfo s,GResource g where "
				+ "s.id=g.detailId and g.resourceType=? "; 
		QueryFeature queryFeature = queryFeatureService.build(searchForm, "s.");
		if (!queryFeature.isEmpty())
		{
			hql += " and ";
			hql += StringUtils.join(queryFeature.getQls(), " and ");
		}
		hql += " order by s.lastName";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ResourceType.SURROGACY);
		paramList.addAll(Arrays.asList(queryFeature.getParams()));
		Object[] params = paramList.toArray();
		log.info("hql: " + hql);
		Long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		@SuppressWarnings("unchecked")
		List<Object[]> queryForList = (List<Object[]>) this.hibernateTemplate.queryForList(hql, start, limit, params);
		List<SurrogacyInfo> surrogacyInfoList = new ArrayList<SurrogacyInfo>();
		if(queryForList!=null){
			for(int i=0;i<queryForList.size();i++){
				Object[] objects = queryForList.get(i);
				SurrogacyInfo surrogacyInfo = (SurrogacyInfo)objects[0];
				GResource gresource = (GResource) objects[1];
				surrogacyInfo.setResourceState(gresource.getResourceState());
				surrogacyInfo.setProcessState(gresource.getProcessState());
				surrogacyInfoList.add(surrogacyInfo);
			}
			return new PagingResult<Object>(totalCount,surrogacyInfoList.toArray());
		}
		return new PagingResult<Object>(0, null);
	}
	
	public PagingResult<Object> getPagingSurrogacysByCreatorQueryForm(String creatorId, SurrogacySearchForm searchForm, int start, int limit)
	{
		String hql = "from SurrogacyInfo s,GResource g where "
				+ "s.id=g.detailId and g.resourceType=? and g.creatorId=?"; 
		QueryFeature queryFeature = queryFeatureService.build(searchForm, "s.");
		if (!queryFeature.isEmpty())
		{
			hql += " and ";
			hql += StringUtils.join(queryFeature.getQls(), " and ");
		}
		hql += " order by s.lastName";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ResourceType.SURROGACY);
		paramList.add(creatorId);
		paramList.addAll(Arrays.asList(queryFeature.getParams()));
		Object[] params = paramList.toArray();
		log.info("hql: " + hql);
		Long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		@SuppressWarnings("unchecked")
		List<Object[]> queryForList = (List<Object[]>) this.hibernateTemplate.queryForList(hql, start, limit, params);
		List<SurrogacyInfo> surrogacyInfoList = new ArrayList<SurrogacyInfo>();
		if(queryForList!=null){
			for(int i=0;i<queryForList.size();i++){
				Object[] objects = queryForList.get(i);
				SurrogacyInfo surrogacyInfo = (SurrogacyInfo)objects[0];
				GResource gresource = (GResource) objects[1];
				surrogacyInfo.setResourceState(gresource.getResourceState());
				surrogacyInfo.setProcessState(gresource.getProcessState());
				surrogacyInfoList.add(surrogacyInfo);
			}
			return new PagingResult<Object>(totalCount,surrogacyInfoList.toArray());
		}
		return new PagingResult<Object>(0, null);
	}
	
	public PagingResult<Object> getPagingSurrogacysByQueryFormEnable(SurrogacySearchForm searchForm, int start, int limit)
	{
		String hql = "from SurrogacyInfo s,GResource g where "
				+ "s.id=g.detailId and g.resourceType=? and g.resourceState=? and g.processState=?"; 
		QueryFeature queryFeature = queryFeatureService.build(searchForm, "s.");
		if (!queryFeature.isEmpty())
		{
			hql += " and ";
			hql += StringUtils.join(queryFeature.getQls(), " and ");
		}
		hql += " order by s.lastName";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ResourceType.SURROGACY);
		paramList.add(ResourceState.ENABLE);
		paramList.add(ProcessState.FREE);
		paramList.addAll(Arrays.asList(queryFeature.getParams()));
		Object[] params = paramList.toArray();
		log.info("hql: " + hql);
		Long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		@SuppressWarnings("unchecked")
		List<Object[]> queryForList = (List<Object[]>) this.hibernateTemplate.queryForList(hql, start, limit, params);
		List<SurrogacyInfo> surrogacyInfoList = new ArrayList<SurrogacyInfo>();
		if(queryForList!=null){
			for(int i=0;i<queryForList.size();i++){
				Object[] objects = queryForList.get(i);
				SurrogacyInfo surrogacyInfo = (SurrogacyInfo)objects[0];
				GResource gresource = (GResource) objects[1];
				surrogacyInfo.setProcessState(gresource.getProcessState());
				surrogacyInfoList.add(surrogacyInfo);
			}
			return new PagingResult<Object>(totalCount,surrogacyInfoList.toArray());
		}
		return new PagingResult<Object>(0, null);
	}

	public PagingResult<Object> getPagingSurrogacyByCustomerIdAndName(
			String userId, String name, int start, int limit) {
		String hql = "from SurrogacyInfo s,GResource g where "
				+ "s.id=g.detailId and g.resourceType=? and g.customerId=?"; 
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(ResourceType.SURROGACY);
		paramsList.add(userId);
		if(!StringUtils.isBlank(name)){
			hql+=" and s.lastName=?";
			paramsList.add(name);
		}
		Object[] params = paramsList.toArray();
		log.info("hql: " + hql);
		Long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		@SuppressWarnings("unchecked")
		List<Object[]> queryForList = (List<Object[]>) this.hibernateTemplate.queryForList(hql, start, limit, params);
		List<SurrogacyInfo> surrogacyInfoList = new ArrayList<SurrogacyInfo>();
		if(queryForList!=null){
			for(int i=0;i<queryForList.size();i++){
				Object[] objects = queryForList.get(i);
				SurrogacyInfo surrogacyInfo = (SurrogacyInfo)objects[0];
				GResource gresource = (GResource) objects[1];
				surrogacyInfo.setProcessState(gresource.getProcessState());
				surrogacyInfoList.add(surrogacyInfo);
			}
			return new PagingResult<Object>(totalCount,surrogacyInfoList.toArray());
		}
		return new PagingResult<Object>(0, null);
	}
}
