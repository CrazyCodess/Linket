package com.linkit.garsi.common.authorize.vo;

import org.polaris.framework.authorize.vo.Role;

/**
 * 缓存于HTTP会话的用户信息
 * 
 * @author wang.sheng
 * 
 */
public class Userinfo
{
	public static final String KEY = "userinfo";

	private String userId;
	private String userName;
	private Role[] roles;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Role[] getRoles()
	{
		return roles;
	}

	public void setRoles(Role[] roles)
	{
		this.roles = roles;
	}

}
