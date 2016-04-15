package com.linkit.garsi.common;

/**
 * 抽象资源
 * 
 * @author wang.sheng
 * 
 */
public interface IResource
{
	/**
	 * 获取资源摘要
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * 获取资源类型
	 * 
	 * @return
	 */
	String getResourceType();
}
