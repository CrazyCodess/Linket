package com.linkit.garsi.manager.vo;

/**
 * 账号包装器
 * 
 * @author wang.sheng
 * 
 */
public class AccountWrapper implements Account
{
	private String userId;
	private String userName;
	/**
	 * 对于公司账号,该字段表示为公司业务类型,例如卵子库或代母库. 或者两者兼而有之(逗号隔开)<br>
	 * 对于顾客账号,该字段表示为顾客的业务选择范围. 如果有多个业务,则使用逗号隔开<br>
	 */
	private String userType;

	@Override
	public String getUserId()
	{
		return userId;
	}

	@Override
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

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

}
