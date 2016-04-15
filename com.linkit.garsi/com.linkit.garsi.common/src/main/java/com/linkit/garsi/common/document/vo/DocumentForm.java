package com.linkit.garsi.common.document.vo;

import org.springframework.web.multipart.MultipartFile;

public class DocumentForm
{
	private MultipartFile file;
	private String type;
	private String title;

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

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
