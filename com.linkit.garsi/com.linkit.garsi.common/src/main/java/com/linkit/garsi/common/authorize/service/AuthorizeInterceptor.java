package com.linkit.garsi.common.authorize.service;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.service.AuthorizeService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.exception.ResourceNotFoundException;
import com.linkit.garsi.common.utils.RoleUtils;

/**
 * 授权拦截器
 * 
 * @author wang.sheng
 * 
 */
@Service
public class AuthorizeInterceptor implements HandlerInterceptor
{
	/**
	 * 会话超时时间
	 */
	private static final int SESSION_TIMEOUT = 60 * 60;

	@Resource
	private AuthorizeService authorizeService;
	/**
	 * 不需要鉴权的模块
	 */
	@Resource
	private Set<String> ignoreModuleSet;

	Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String uri = request.getRequestURI();
		String[] array = StringUtils.split(uri, "/");
		String module = array[1];
		if (ignoreModuleSet.contains(module))
		{
			// 无需鉴权的模块
			return true;
		}
		HttpSession session = request.getSession(true);
		if (session.getMaxInactiveInterval() < SESSION_TIMEOUT)
		{
			// 设置超时时间
			session.setMaxInactiveInterval(SESSION_TIMEOUT);
		}
		Userinfo userinfo = (Userinfo) session.getAttribute(Userinfo.KEY);
		if (userinfo == null)
		{
			// 未登录的用户,返回鉴权失败
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			log.warn("Userinfo in session is not found! request from " + request.getRemoteAddr());
			return false;
		}
		if (RoleUtils.isAdminRole(userinfo))
		{
			// 是超级用户,不做鉴权
			return true;
		}
		String method = request.getMethod();
		log.info("uri: " + uri + ", method: " + method + ", Module: " + module);
		boolean writable = true;
		if (StringUtils.equals(method, "GET"))
		{
			// 只读操作
			writable = false;
		}
		Set<String> roleIdSet = authorizeService.getIntersectRoleIdSet(module, userinfo.getUserId(), writable);
		if (roleIdSet.isEmpty())
		{
			// 无交叉角色, 无权限
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			log.warn("User: " + userinfo.getUserName() + " is UnAuthorized for Module: " + module + "! request from " + request.getRemoteAddr());
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		if (ex instanceof ResourceNotFoundException)
		{
			// 找不到资源
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
