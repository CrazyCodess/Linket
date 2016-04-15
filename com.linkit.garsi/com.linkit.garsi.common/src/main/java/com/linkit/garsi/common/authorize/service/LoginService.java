package com.linkit.garsi.common.authorize.service;

import javax.annotation.Resource; 

import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.authorize.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.authorize.vo.LoginForm;

/**
 * 授权管理Service
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
//REQUIRED:业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
public class LoginService
{
	@Resource
	private AuthorizeService authorizeService;

	/**
	 * 根据用户登录账号和密码查询User实例
	 * 
	 * @param account
	 * @return
	 */
	public User findUserByAccount(LoginForm loginForm)
	{
		return authorizeService.findUserByNameAndPassword(loginForm.getUsername(), loginForm.getPassword());
	}

	/**
	 * 根据用户获得角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public Role[] getRolesByUser(String userId)
	{
		return authorizeService.getRolesByUser(userId);
	}

}
