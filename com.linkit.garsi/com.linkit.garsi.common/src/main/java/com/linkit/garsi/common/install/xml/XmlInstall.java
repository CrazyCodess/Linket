package com.linkit.garsi.common.install.xml;

import java.util.Map;

import org.polaris.framework.common.xml.annotation.XmlMap;

public class XmlInstall
{
	@XmlMap(key = "name", name = "role", valueClass = XmlRole.class)
	private Map<String, XmlRole> roleMap;
	@XmlMap(key = "name", name = "module", valueClass = XmlModule.class)
	private Map<String, XmlModule> moduleMap;
	@XmlMap(key = "name", name = "user", valueClass = XmlUser.class)
	private Map<String, XmlUser> userMap;

	public Map<String, XmlRole> getRoleMap()
	{
		return roleMap;
	}

	public Map<String, XmlModule> getModuleMap()
	{
		return moduleMap;
	}

	public Map<String, XmlUser> getUserMap()
	{
		return userMap;
	}

}
