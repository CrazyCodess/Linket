package com.linkit.garsi.manager.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 顾客
 * 
 * @author wang.sheng
 * 
 */
@Entity
@Table
public class Customer implements Account
{
	/**
	 * 主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 32, nullable = false)
	private String userId;
	/**
	 * 需求范围,用需求ID表示,多个之间用逗号隔开
	 */
	@Column(length = 50)
	private String demandType;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public String getUserId()
	{
		return userId;
	}

	@Override
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getDemandType()
	{
		return demandType;
	}

	public void setDemandType(String demandType)
	{
		this.demandType = demandType;
	}

}
