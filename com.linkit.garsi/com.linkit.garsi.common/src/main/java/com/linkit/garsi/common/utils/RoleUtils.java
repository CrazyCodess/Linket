package com.linkit.garsi.common.utils;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.authorize.vo.Role;

import com.linkit.garsi.common.RoleType;
import com.linkit.garsi.common.authorize.vo.Userinfo;

/**
 * 角色工具类
 * 
 * @author wang.sheng
 * 
 */
public final class RoleUtils
{
	private RoleUtils()
	{
	}

	/**
	 * 是否超级用户
	 * 
	 * @param userinfo
	 * @return
	 */
	public static boolean isAdminRole(Userinfo userinfo)
	{
		return StringUtils.equals(userinfo.getUserName(), RoleType.ADMIN);
	}

	/**
	 * 是否Garsi管理员
	 * 
	 * @param userinfo
	 * @return
	 */
	public static boolean isGarsiRole(Userinfo userinfo)
	{
		for (Role role : userinfo.getRoles())
		{
			if (StringUtils.equals(role.getName(), RoleType.GARSI))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否公司用户
	 * 
	 * @param userinfo
	 * @return
	 */
	public static boolean isCompanyRole(Userinfo userinfo)
	{
		return containsCompanyRole(userinfo.getRoles());
	}

	public static boolean containsCompanyRole(Role[] roles)
	{
		for (Role role : roles)
		{
			if (StringUtils.equals(role.getName(), RoleType.EGG) || StringUtils.equals(role.getName(), RoleType.SPERM)
					|| StringUtils.equals(role.getName(), RoleType.SURROGACY))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean containsCustomerRole(Role[] roles)
	{
		for (Role role : roles)
		{
			if (StringUtils.equals(role.getName(), RoleType.CUSTOMER))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否顾客用户
	 * 
	 * @param userinfo
	 * @return
	 */
	public static boolean isCustomerRole(Userinfo userinfo)
	{
		return containsCustomerRole(userinfo.getRoles());
	}
}
