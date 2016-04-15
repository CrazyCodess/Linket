package com.linkit.garsi.common;

/**
 * 顾客需求
 * 
 * @author wang.sheng
 * 
 */
public interface Demand
{
	/**
	 * 获取需求的资源类型
	 * 
	 * @return
	 */
	String getResourceType();

	/**
	 * 获取最终匹配的资源ID
	 * 
	 * @return
	 */
	String getResourceId();

	/**
	 * 获取需求提出者(顾客)的User ID
	 * 
	 * @return
	 */
	String getUserId();
}
