package com.linkit.garsi.common.document.vo;

import org.polaris.framework.common.dao.query.Compare;
import org.polaris.framework.common.dao.query.Query;

public class QueryForm
{
	@Query(compare = Compare.Like)
	private String type;
	@Query(compare = Compare.Like)
	private String title;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}
