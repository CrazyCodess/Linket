package com.linkit.garsi.common.resource.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;

/**
 * Garsi下的资源,主要有三种类型<br>
 * 起名为GResource,是为了和Spring框架中的Resource区别
 * 
 * @author wang.sheng
 * 
 */
@Entity
@Table
public class GResource
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	/**
	 * 创建者,对应资源提供者(代母公司,精子公司或卵子公司)User ID
	 */
	@Column(length = 32, nullable = false)
	private String creatorId;
	/**
	 * 选择此资源的顾客的User ID
	 */
	@Column(length = 32)
	private String customerId;
	/**
	 * 资源类型,参照ResourceType中的定义
	 */
	@Column(length = 32, nullable = false)
	private String resourceType;
	/**
	 * 资源标题
	 */
	@Column(length = 100, nullable = false)
	private String title;
	/**
	 * 详细信息的主键,对应代母库,精子库,卵子库的主表主键
	 */
	@Column(length = 32, nullable = false)
	private String detailId;
	/**
	 * 资源状态
	 */
	@Column(length = 32, nullable = false)
	private String resourceState = ResourceState.ENABLE;
	/**
	 * 流程状态
	 */
	@Column(length = 32, nullable = false)
	private String processState = ProcessState.FREE;
	/**
	 * 资源创建时间
	 */
	@Column
	private long createTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCreatorId()
	{
		return creatorId;
	}

	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}

	public String getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public String getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(String resourceType)
	{
		this.resourceType = resourceType;
	}

	public String getDetailId()
	{
		return detailId;
	}

	public void setDetailId(String detailId)
	{
		this.detailId = detailId;
	}

	public String getResourceState()
	{
		return resourceState;
	}

	public void setResourceState(String resourceState)
	{
		this.resourceState = resourceState;
	}

	public String getProcessState()
	{
		return processState;
	}

	public void setProcessState(String processState)
	{
		this.processState = processState;
	}

	public long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
