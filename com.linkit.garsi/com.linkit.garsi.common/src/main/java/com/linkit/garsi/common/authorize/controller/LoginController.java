package com.linkit.garsi.common.authorize.controller;

import javax.annotation.Resource; 
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.authorize.vo.User;
import org.polaris.framework.common.rest.FormResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.linkit.garsi.common.authorize.service.LoginService;
import com.linkit.garsi.common.authorize.vo.LoginForm;
import com.linkit.garsi.common.authorize.vo.Userinfo;

/**
 * 登录模块
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/login")
public class LoginController
{
	@Resource
	private LoginService loginService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 登录
	 * 
	 * @param account
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public FormResult login(@RequestBody LoginForm loginForm, HttpSession httpSession)
	{
		log.info("login user: " + loginForm.getUsername());
		User user = loginService.findUserByAccount(loginForm);
		FormResult formResult = new FormResult();
		if (user == null)
		{
			// 用户不存在
			formResult.setSuccess(false);
			formResult.setMessage("User is not exists or invalid password!");
		}
		else
		{
			// 验证通过
			formResult.setSuccess(true);
			Role[] roles = loginService.getRolesByUser(user.getId());
			Userinfo userinfo = new Userinfo();
			userinfo.setUserId(user.getId());
			userinfo.setUserName(user.getName());
			userinfo.setRoles(roles);
			httpSession.setAttribute(Userinfo.KEY, userinfo);
			formResult.setData(userinfo);// 返回前端用户信息
		}
		return formResult;
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public FormResult logout(HttpSession httpSession)
	{
		Userinfo userinfo = (Userinfo) httpSession.getAttribute(Userinfo.KEY);
		if (userinfo != null)
		{
			httpSession.removeAttribute(Userinfo.KEY);
			log.info("logout user: " + userinfo.getUserName());
		}
		else
		{
			log.warn("Userinfo in HttpSession is not found!");
		}
		FormResult formResult = new FormResult();
		formResult.setSuccess(true);
		return formResult;
	}
}
