package com.linkit.garsi.manager.vo;

import org.polaris.framework.common.dao.query.Compare;
import org.polaris.framework.common.dao.query.Query;

/**
 * 代母需求表单
 * 
 * @author wang.sheng
 * 
 */
public class SurrogacyDemandForm
{
	@Query
	private String country;
	@Query
	private String nation;
	@Query
	private String hairColor;
	@Query
	private String eyeColor;
	@Query(value = "height", compare = Compare.GtEq)
	private Double minHeight;
	@Query(value = "height", compare = Compare.LtEq)
	private Double maxHeight;
	@Query(value = "weight", compare = Compare.GtEq)
	private Double minWeight;
	@Query(value = "weight", compare = Compare.LtEq)
	private Double maxWeight;
	@Query
	private String bloodType;
	@Query
	private String education;
	@Query
	private String job;

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

	public Double getMinHeight()
	{
		return minHeight;
	}

	public void setMinHeight(Double minHeight)
	{
		this.minHeight = minHeight;
	}

	public Double getMaxHeight()
	{
		return maxHeight;
	}

	public void setMaxHeight(Double maxHeight)
	{
		this.maxHeight = maxHeight;
	}

	public Double getMinWeight()
	{
		return minWeight;
	}

	public void setMinWeight(Double minWeight)
	{
		this.minWeight = minWeight;
	}

	public Double getMaxWeight()
	{
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight)
	{
		this.maxWeight = maxWeight;
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

}
