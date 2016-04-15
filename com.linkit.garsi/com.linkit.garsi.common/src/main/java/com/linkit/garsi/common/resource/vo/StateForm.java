package com.linkit.garsi.common.resource.vo;

/**
 * 来自前端提交的状态对象
 * 
 * @author wang.sheng
 * 
 */
public class StateForm
{
	private String resourceId;
	private String state;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
