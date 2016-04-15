package com.linkit.garsi.manager.vo;

/**
 * 资源选择表单
 * 
 * @author wang.sheng
 * 
 */
public class ResourceSelectForm
{
	private String resourceType;
	private String[] resourceIds;

	public String getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(String resourceType)
	{
		this.resourceType = resourceType;
	}

	public String[] getResourceIds()
	{
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds)
	{
		this.resourceIds = resourceIds;
	}

}
