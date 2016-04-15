package com.linkit.garsi.common.resource.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资源二进制附件
 * 
 * @author wang.sheng
 * 
 */
@Entity
@Table
public class Attachment
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	/**
	 * 资源主键
	 */
	@Column(length = 32, nullable = false)
	private String resourceId;
	/**
	 * 二进制内容主键
	 */
	@Column(length = 32, nullable = false)
	private String contentId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getContentId()
	{
		return contentId;
	}

	public void setContentId(String contentId)
	{
		this.contentId = contentId;
	}

}
