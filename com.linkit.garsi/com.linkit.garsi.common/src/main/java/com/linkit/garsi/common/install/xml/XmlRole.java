package com.linkit.garsi.common.install.xml;

import org.polaris.framework.common.xml.annotation.XmlAttribute;

public class XmlRole
{
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String remark;
	@XmlAttribute
	private boolean writable;

	public String getName()
	{
		return name;
	}

	public String getRemark()
	{
		return remark;
	}

	public boolean isWritable()
	{
		return writable;
	}

}
