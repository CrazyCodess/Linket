package com.linkit.garsi.common.install;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.Module;
import org.polaris.framework.authorize.vo.ModuleRoleRel;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.authorize.vo.User;
import org.polaris.framework.authorize.vo.UserRoleRel;
import org.polaris.framework.common.xml.XmlParseService;
import org.springframework.stereotype.Service;

import com.linkit.garsi.common.install.xml.XmlInstall;
import com.linkit.garsi.common.install.xml.XmlModule;
import com.linkit.garsi.common.install.xml.XmlRole;
import com.linkit.garsi.common.install.xml.XmlUser;

/**
 * 系统安装服务
 * 
 * @author wang.sheng
 * 
 */
@Service
public class InstallService
{
	public static final String INSTALL_CONFIG_PATH = "META-INF/install/install.xml";
	public static final String SUPER_USERNAME = "admin";

	@Resource
	private XmlParseService xmlParseService;
	@Resource
	private AuthorizeService authorizeService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 自动执行的安装服务
	 */
	@PostConstruct
	protected void install()
	{
		User superUser = authorizeService.findUserByName(SUPER_USERNAME);
		if (superUser != null)
		{
			// 已存在超级用户,则说明已经安装过
			return;
		}
		// 创建超级用户
		superUser = new User();
		superUser.setName("admin");
		superUser.setPassword("admin");
		superUser.setRemark("Super User");
		authorizeService.insert(new User[] { superUser });
		log.info("---- start install system");
		XmlInstall xmlInstall = this.loadXmlInstall();
		Map<String, Role> roleMap = new HashMap<String, Role>();
		for (XmlRole xmlRole : xmlInstall.getRoleMap().values())
		{
			Role role = new Role();
			role.setName(xmlRole.getName());
			role.setRemark(xmlRole.getRemark());
			role.setWritable(xmlRole.isWritable());
			roleMap.put(xmlRole.getName(), role);
		}
		// 添加角色
		authorizeService.insert(roleMap.values().toArray(new Role[0]));
		log.info("Roles installed successful!");
		List<Module> moduleList = new ArrayList<Module>();
		for (XmlModule xmlModule : xmlInstall.getModuleMap().values())
		{
			Module module = new Module();
			module.setName(xmlModule.getName());
			module.setRemark(xmlModule.getRemark());
			moduleList.add(module);
		}
		// 添加模块
		authorizeService.insert(moduleList.toArray(new Module[0]));
		log.info("Modules installed successful!");
		List<ModuleRoleRel> moduleRoleRelList = new ArrayList<ModuleRoleRel>();
		for (Module module : moduleList)
		{
			String moduleId = module.getId();
			String[] roleNames = xmlInstall.getModuleMap().get(module.getName()).getRoles();
			for (String roleName : roleNames)
			{
				Role role = roleMap.get(roleName);
				if (role == null)
				{
					log.warn("Role: " + roleName + " is not config!");
					continue;
				}
				ModuleRoleRel rel = new ModuleRoleRel();
				rel.setModuleId(moduleId);
				rel.setRoleId(role.getId());
				moduleRoleRelList.add(rel);
			}
		}
		// 添加模块和角色关系
		authorizeService.insert(moduleRoleRelList.toArray(new ModuleRoleRel[0]));
		log.info("ModuleRoleRels installed successful!");
		List<User> userList = new ArrayList<User>();
		for (XmlUser xmlUser : xmlInstall.getUserMap().values())
		{
			User user = new User();
			user.setName(xmlUser.getName());
			user.setRemark(xmlUser.getRemark());
			user.setPassword(xmlUser.getName());
			userList.add(user);
		}
		// 添加用户
		authorizeService.insert(userList.toArray(new User[0]));
		log.info("Users installed successful!");
		List<UserRoleRel> userRoleRelList = new ArrayList<UserRoleRel>();
		for (User user : userList)
		{
			String userId = user.getId();
			String[] roleNames = xmlInstall.getUserMap().get(user.getName()).getRoles();
			for (String roleName : roleNames)
			{
				Role role = roleMap.get(roleName);
				if (role == null)
				{
					log.warn("Role: " + roleName + " is not config!");
					continue;
				}
				UserRoleRel rel = new UserRoleRel();
				rel.setUserId(userId);
				rel.setRoleId(role.getId());
				userRoleRelList.add(rel);
			}
		}
		// 添加用户和角色关系
		authorizeService.insert(userRoleRelList.toArray(new UserRoleRel[0]));
		log.info("UserRoleRels installed successful!");
		log.info("---- system installed successful!");
	}

	private XmlInstall loadXmlInstall()
	{
		InputStream is = null;
		try
		{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(INSTALL_CONFIG_PATH);
			return xmlParseService.parseObject(XmlInstall.class, is);
		}
		catch (Exception e)
		{
			throw new RuntimeException("loadXmlInstall failed!", e);
		}
		finally
		{
			IOUtils.closeQuietly(is);
		}
	}
}
