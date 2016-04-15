package com.linkit.garsi.common.authorize.controller;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.common.rest.FormResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.linkit.garsi.common.authorize.service.SystemService;
import com.linkit.garsi.common.authorize.vo.PasswordForm;
import com.linkit.garsi.common.authorize.vo.Userinfo;

/**
 * 系统模块
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/system")
@SessionAttributes(Userinfo.KEY)
public class SystemController
{
	@Resource
	private AuthorizeService authorizeService;
	@Resource
	private SystemService systemService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 获取全部角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public Role[] getRoles()
	{
		return authorizeService.getRoles();
	}

	/**
	 * 修改密码
	 * 
	 * @param password
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public FormResult modifyPassword(@RequestBody PasswordForm passwordForm, @ModelAttribute Userinfo userinfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			systemService.modifyPassword(userinfo.getUserName(), passwordForm.getOldPassword(), passwordForm.getNewPassword());
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			// 前端数据校验错误
			log.error("modifyPassword failed!", e);
			formResult.copyErrors(e);
			formResult.setMessage("modifyPassword failed!");
			formResult.setSuccess(false);
		}
		catch (Exception e)
		{
			log.error("modifyPassword failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}
}
