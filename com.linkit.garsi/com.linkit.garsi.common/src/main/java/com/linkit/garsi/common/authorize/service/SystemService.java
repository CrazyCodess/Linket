package com.linkit.garsi.common.authorize.service;

import javax.annotation.Resource;

import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.User;
import org.polaris.framework.common.rest.FormException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SystemService
{
	@Resource
	private AuthorizeService authorizeService;

	/**
	 * 修改用户密码
	 * 
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 */
	public void modifyPassword(String userName, String oldPassword, String newPassword)
	{
		User user = authorizeService.findUserByNameAndPassword(userName, oldPassword);
		if (user == null)
		{
			// 用户不存在或密码错误
			throw new FormException("UserName: " + userName + " is not exists or invalid password!");
		}
		authorizeService.modifyPassword(user.getId(), newPassword);
	}
}
