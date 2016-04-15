package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggForm;

@Repository
public class EggDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(Egg egg)
	{
		this.hibernateTemplate.save(egg);
	}

	/**
	 * 删除
	 * 
	 * @param EggId
	 */
	public void deleteEgg(String eggId)
	{
		Object[] params = new Object[] { eggId };
		this.hibernateTemplate.executeUpdate("delete from Egg t where t.id=?", params);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public Egg getEggById(String eggId)
	{
		return this.hibernateTemplate.queryForObject("from Egg t where t.id =?", new Object[] { eggId }, Egg.class);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public Egg[] getAllEgg()
	{
		return this.hibernateTemplate.queryForArray("from Egg t order by t.id desc", null, Egg.class);
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
	public PagingResult<Object> getPagingEggByAttr(EggForm egg, int start, int limit)
	{
		String hql = "select distinct  t,g from Egg t ,GResource g,EggEduInfo e   where 1=1 and t.id = e.resourceId and t.id=g.detailId ";
		List<Object> params = new ArrayList<Object>();
		if (egg.getAgeStart() != null)
		{
			hql += " and t.age >= ?";
			params.add(egg.getAgeStart());
		}
		
		if (egg.getAgeEnd() != null)
		{
			hql += " and t.age <= ?";
			params.add(egg.getAgeEnd());
		}
		
		if (egg.getHeightStart()!= null)
		{
			hql += " and t.height >= ?";
			params.add(egg.getHeightStart());
		}
		
		if (egg.getHeightEnd()!= null)
		{
			hql += " and t.height <= ?";
			params.add(egg.getHeightEnd());
		}
		
		
		if (egg.getWeightStart()!= null)
		{
			hql += " and t.weight >= ?";
			params.add(egg.getWeightStart());
		}
		
		if (egg.getWeightEnd()!= null)
		{
			hql += " and t.weight <= ?";
			params.add(egg.getWeightEnd());
		}
		
		
		if (StringUtil.isNotBlank(egg.getBmi()))
		{
			hql += " and t.bmi = ?";
			params.add(egg.getBmi());
		}
		
		if (StringUtil.isNotBlank(egg.getRace()))
		{
			hql += " and t.race = ?";
			params.add(egg.getRace());
		}
		
		if(StringUtil.isNotBlank(egg.getOccupation())){
			hql +=" and e.occupation =?";
			params.add(egg.getOccupation());		
		}
		
		
		if(StringUtil.isNotBlank(egg.getCreatorId())){
			hql +=" and g.creatorId=?";
			params.add(egg.getCreatorId());		
		}
		
		if(StringUtil.isNotBlank(egg.getCustomerId())){
			hql +=" and g.customerId=?";
			params.add(egg.getCustomerId());		
		}
		
		if(StringUtil.isNotBlank(egg.getProcessState())){
			hql +=" and g.processState=?";
			params.add(egg.getProcessState());		
		}
		if(StringUtil.isNotBlank(egg.getResourceState())){
			hql +=" and g.resourceState=?";
			params.add(egg.getResourceState());		
		}
		
		hql += " order by t.updateTime desc";

		
		
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params.toArray());
		
		List<Object[]> queryForList = (List<Object[]>) this.hibernateTemplate.queryForList(hql, start, limit, params.toArray());
		
		List<Egg> eggList = new ArrayList<Egg>();
		if(queryForList!=null){
			for(int i=0;i<queryForList.size();i++){
				Object[] objects = queryForList.get(i);
				Egg eggInfo = (Egg)objects[0];
				GResource gresource = (GResource) objects[1];
				eggInfo.setProcessState(gresource.getProcessState());
				eggInfo.setResourceState(gresource.getResourceState());
				eggList.add(eggInfo);
			}
			return new PagingResult<Object>(totalCount,eggList.toArray());
		}
		return new PagingResult<Object>(0, null);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEgg(Egg egg)
	{
		this.hibernateTemplate.update(egg);
	}
	
	public PagingResult<Object> getAllCustomerSelect(String eggName,String surrName,int start, int limit){
		
		String sql = "select e.id, e.fullname as name,g.resourceType, g.processState,g.resourceState,u.name as username";
		sql+=" from egg e,gresource g,user u where e.id=g.detailId and g.customerId=u.id and g.processState in('selected','finnally')";
		if(StringUtils.isNotEmpty(eggName)){
			sql+=" and e.fullname  like '%"+eggName+"%'";
		}
		if(StringUtils.isNotEmpty(surrName)){
			sql+=" and u.name like '%"+surrName+"%'";
		}
		sql+=" union"; 
		sql+=" select s.id, s.name ,g.resourceType, g.processState,g.resourceState,u.name as username";
		sql+=" from SurrogacyInfo s,gresource g,user u where s.id=g.detailId and g.customerId=u.id and g.processState in('selected','finnally')";
		if(StringUtils.isNotEmpty(eggName)){
			sql+=" and s.name like '%"+eggName+"%'";
		}
		if(StringUtils.isNotEmpty(surrName)){
			sql+=" and u.name like '%"+surrName+"%'";
		}
		long totalCount = this.hibernateTemplate.getSqlQueryCount(sql);
		List<Object[]> results = this.hibernateTemplate.getSqlQuery(sql, start, limit);
		
		if(CollectionUtils.isEmpty(results)){
			return new PagingResult<Object>(totalCount,ArrayUtils.EMPTY_OBJECT_ARRAY);
		}else{
			return new PagingResult<Object>(totalCount,results.toArray());
		}
		
	}

}
