package com.linkit.garsi.manager.vo;

/**
 * 账号表单
 * 
 * @author wang.sheng
 * 
 */
public class AccountForm
{
	private String username;
	private String password;
	private String roleId;
	/**
	 * 角色为公司时,公司名称
	 */
	private String companyName;
	/**
	 * 角色为顾客时,顾客需求类型
	 */
	private String[] demandTypes;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String[] getDemandTypes()
	{
		return demandTypes;
	}

	public void setDemandTypes(String[] demandTypes)
	{
		this.demandTypes = demandTypes;
	}

}
