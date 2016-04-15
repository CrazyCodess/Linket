package com.linkit.garsi.common.resource.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.resource.vo.Attachment;

@Repository
public class AttachmentDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public Attachment[] getAttachmentsByResourceId(String resourceId)
	{
		return this.hibernateTemplate.queryForArray("from Attachment t where t.resourceId=?", new Object[] { resourceId }, Attachment.class);
	}

	public Attachment[] getAttachmentsByDetailId(String detailId)
	{
		return this.hibernateTemplate.queryForArray("from Attachment a where a.resourceId in(select r.id from GResource r where r.detailId=?)",
				new Object[] { detailId }, Attachment.class);
	}

	public void delete(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from Attachment t where t.id=?", new Object[] { id });
	}

	public void deleteByResourceId(String resourceId)
	{
		this.hibernateTemplate.executeUpdate("delete from Attachment t where t.resourceId=?", new Object[] { resourceId });
	}

	public void deleteByDetailId(String detailId)
	{
		this.hibernateTemplate.executeUpdate("delete from Attachment a where a.resourceId in(select r.id from GResource r where r.detailId=?)",
				new Object[] { detailId });
	}

	public void insert(Attachment attachment)
	{
		this.hibernateTemplate.save(attachment);
	}
}
