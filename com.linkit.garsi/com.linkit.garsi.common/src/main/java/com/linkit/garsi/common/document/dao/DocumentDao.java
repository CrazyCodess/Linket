package com.linkit.garsi.common.document.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.dao.query.QueryFeature;
import org.polaris.framework.common.dao.query.QueryFeatureService;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.document.vo.QueryForm;

@Repository
public class DocumentDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private QueryFeatureService queryFeatureService;

	public void insert(Document document)
	{
		hibernateTemplate.save(document);
	}

	public Document getDocument(String id)
	{
		return hibernateTemplate.queryForObject("from Document t where t.id=?", new Object[] { id }, Document.class);
	}

	public Document[] getDocumentsByUserId(String userId)
	{
		return this.hibernateTemplate.queryForArray("from Document t where t.userId=? order by t.time desc", new Object[] { userId }, Document.class);
	}

	public PagingResult<Document> getFinallyDocumentsByCustomer(String userId, QueryForm queryForm, int start, int limit)
	{
		QueryFeature queryFeature = queryFeatureService.build(queryForm, "t.");
		String hql = "from Document t where t.userId in(select g.creatorId from GResource g where g.customerId=? and g.processState=?)";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		paramList.add(ProcessState.FINALLY);
		if (!queryFeature.isEmpty())
		{
			hql += " and ";
			hql += StringUtils.join(queryFeature.getQls(), " and ");
			paramList.addAll(queryFeature.getParamList());
		}
		hql += " order by t.time desc";
		Object[] params = paramList.toArray();
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		Document[] documents = this.hibernateTemplate.queryForArray(hql, start, limit, params, Document.class);
		return new PagingResult<Document>(totalCount, documents);
	}

	public PagingResult<Document> getDocumentsByUserId(String userId, QueryForm queryForm, int start, int limit)
	{
		QueryFeature queryFeature = queryFeatureService.build(queryForm, "t.");
		String hql = "from Document t where t.userId=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(userId);
		if (!queryFeature.isEmpty())
		{
			hql += " and ";
			hql += StringUtils.join(queryFeature.getQls(), " and ");
			paramList.addAll(queryFeature.getParamList());
		}
		hql += " order by t.time desc";
		Object[] params = paramList.toArray();
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		Document[] documents = this.hibernateTemplate.queryForArray(hql, start, limit, params, Document.class);
		return new PagingResult<Document>(totalCount, documents);
	}

	public void delete(String id)
	{
		hibernateTemplate.executeUpdate("delete from Document t where t.id=?", new Object[] { id });
	}

	public void deleteByUserId(String userId)
	{
		hibernateTemplate.executeUpdate("delete from Document t where t.userId=?", new Object[] { userId });
	}

}
