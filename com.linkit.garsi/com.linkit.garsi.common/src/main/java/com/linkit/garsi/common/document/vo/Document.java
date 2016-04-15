package com.linkit.garsi.common.document.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 公司上传的资料文档,主要是协议和法律文件
 * 
 * @author wang.sheng
 * 
 */
@Table
@Entity
public class Document
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	/**
	 * 关联账号ID
	 */
	@Column(length = 32, nullable = false)
	private String userId;
	/**
	 * 关联内容ID
	 */
	@Column(length = 32, nullable = false)
	private String contentId;
	/**
	 * 操作时间
	 */
	@Column
	private long time;
	/**
	 * 资料类型
	 */
	@Column(length = 50)
	private String type;
	/**
	 * 资料标题
	 */
	@Column(length = 100)
	private String title;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getContentId()
	{
		return contentId;
	}

	public void setContentId(String contentId)
	{
		this.contentId = contentId;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
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
