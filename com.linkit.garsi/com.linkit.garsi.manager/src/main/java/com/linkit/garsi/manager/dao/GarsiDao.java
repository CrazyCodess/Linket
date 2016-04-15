package com.linkit.garsi.manager.dao;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.dao.query.QueryFeature;
import org.polaris.framework.common.dao.query.QueryFeatureService;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.manager.vo.EggDemandForm;
import com.linkit.garsi.manager.vo.SurrogacyDemandForm;

/**
 * Garsi管理员使用的DAO
 * 
 * @author wang.sheng
 * 
 */
@Repository
public class GarsiDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private QueryFeatureService queryFeatureService;

	/**
	 * 查询代母库
	 * 
	 * @param surrogacyDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> searchResources(SurrogacyDemandForm surrogacyDemandForm, int start, int limit)
	{
		String hql = "from GResource t where t.detailId in(select s.id from Surrogacy s where ";
		QueryFeature queryFeature = queryFeatureService.build(surrogacyDemandForm, "s.");
		hql += StringUtils.join(queryFeature.getQls(), " and ");
		hql += ") order by t.title";
		Object[] params = queryFeature.getParams();
		long totalCount = hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}

	/**
	 * 查询卵子库
	 * 
	 * @param eggDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> searchResources(EggDemandForm eggDemandForm, int start, int limit)
	{
		String hql = "from GResource t where t.detailId in(select e.id from Egg e where ";
		QueryFeature queryFeature = queryFeatureService.build(eggDemandForm, "e.");
		hql += StringUtils.join(queryFeature.getQls(), " and ");
		hql += ") order by t.title";
		Object[] params = queryFeature.getParams();
		long totalCount = hibernateTemplate.getTotalCount(hql, params);
		GResource[] resources = hibernateTemplate.queryForArray(hql, start, limit, params, GResource.class);
		return new PagingResult<GResource>(totalCount, resources);
	}
}
