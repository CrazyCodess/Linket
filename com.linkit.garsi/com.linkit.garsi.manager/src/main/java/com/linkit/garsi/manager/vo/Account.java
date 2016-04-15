package com.linkit.garsi.manager.vo;

/**
 * 账号信息<br>
 * 在框架鉴权模块的User基础上扩展<br>
 * 
 * @author wang.sheng
 * 
 */

public interface Account
{
	/**
	 * 获取User的ID
	 * 
	 * @return
	 */
	String getUserId();

	void setUserId(String userId);
}
