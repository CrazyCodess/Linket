package com.linkit.garsi.common.install.xml;

import org.polaris.framework.common.xml.annotation.XmlAttribute;

public class XmlUser
{
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String remark;
	@XmlAttribute
	private String[] roles;

	public String getName()
	{
		return name;
	}

	public String getRemark()
	{
		return remark;
	}

	public String[] getRoles()
	{
		return roles;
	}

}
