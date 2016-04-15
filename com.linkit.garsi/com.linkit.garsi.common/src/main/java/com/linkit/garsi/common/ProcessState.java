package com.linkit.garsi.common;

/**
 * 流程状态类型
 * 
 * @author wang.sheng
 * 
 */
public interface ProcessState
{
	/**
	 * 空闲状态
	 */
	String FREE = "free";
	/**
	 * 顾客选中状态
	 */
	String SELECTED = "selected";
	/**
	 * 管理员终选状态
	 */
	String FINALLY = "finally";
}
