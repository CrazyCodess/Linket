package com.linkit.garsi.manager.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.linkit.garsi.common.Demand;
import com.linkit.garsi.common.ResourceType;

/**
 * 顾客精子需求
 * 
 * @author wang.sheng
 * 
 */
@Entity
@Table
public class SpermDemand implements Demand
{
	/**
	 * 主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String country;
	@Column(length = 50)
	private String nation;
	@Column(length = 50)
	private String hairColor;
	@Column(length = 50)
	private String eyeColor;
	@Column
	private double height;
	@Column
	private double weight;
	@Column(length = 50)
	private String bloodType;
	@Column(length = 50)
	private String education;
	@Column(length = 50)
	private String job;
	/**
	 * 流程终结时的资源ID
	 */
	@Column(length = 32)
	private String resourceId;
	@Column(length = 32, nullable = false)
	private String userId;

	@Override
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getNation()
	{
		return nation;
	}

	public void setNation(String nation)
	{
		this.nation = nation;
	}

	public String getHairColor()
	{
		return hairColor;
	}

	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}

	public String getEyeColor()
	{
		return eyeColor;
	}

	public void setEyeColor(String eyeColor)
	{
		this.eyeColor = eyeColor;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public String getBloodType()
	{
		return bloodType;
	}

	public void setBloodType(String bloodType)
	{
		this.bloodType = bloodType;
	}

	public String getEducation()
	{
		return education;
	}

	public void setEducation(String education)
	{
		this.education = education;
	}

	public String getJob()
	{
		return job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	@Override
	public String getResourceType()
	{
		return ResourceType.SPERM;
	}

	public String getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

}
