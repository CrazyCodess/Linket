package com.linkit.garsi.surrogacy.vo;

import org.polaris.framework.common.dao.query.Compare;
import org.polaris.framework.common.dao.query.Query;

public class SurrogacySearchForm
{
	@Query(value = "age", compare = Compare.GtEq)
	private Integer ageMin;
	@Query(value = "age", compare = Compare.LtEq)
	private Integer ageMax;
	@Query(value = "height", compare = Compare.GtEq)
	private Float heightMin;
	@Query(value = "height", compare = Compare.LtEq)
	private Float heightMax;
	@Query(value = "weight", compare = Compare.GtEq)
	private Float weightMin;
	@Query(value = "weight", compare = Compare.LtEq)
	private Float weightMax;
	@Query
	private String ethnicity;
	@Query
	private String nationalOrigin;
	@Query
	private String occupation;
	@Query
	private String beenASurrogateBefore;

	public Integer getAgeMin()
	{
		return ageMin;
	}

	public void setAgeMin(Integer ageMin)
	{
		this.ageMin = ageMin;
	}

	public Integer getAgeMax()
	{
		return ageMax;
	}

	public void setAgeMax(Integer ageMax)
	{
		this.ageMax = ageMax;
	}

	public Float getHeightMin()
	{
		return heightMin;
	}

	public void setHeightMin(Float heightMin)
	{
		this.heightMin = heightMin;
	}

	public Float getHeightMax()
	{
		return heightMax;
	}

	public void setHeightMax(Float heightMax)
	{
		this.heightMax = heightMax;
	}

	public Float getWeightMin()
	{
		return weightMin;
	}

	public void setWeightMin(Float weightMin)
	{
		this.weightMin = weightMin;
	}

	public Float getWeightMax()
	{
		return weightMax;
	}

	public void setWeightMax(Float weightMax)
	{
		this.weightMax = weightMax;
	}

	public String getEthnicity()
	{
		return ethnicity;
	}

	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
	}

	public String getNationalOrigin()
	{
		return nationalOrigin;
	}

	public void setNationalOrigin(String nationalOrigin)
	{
		this.nationalOrigin = nationalOrigin;
	}

	public String getOccupation()
	{
		return occupation;
	}

	public void setOccupation(String occupation)
	{
		this.occupation = occupation;
	}

	public String getBeenASurrogateBefore()
	{
		return beenASurrogateBefore;
	}

	public void setBeenASurrogateBefore(String beenASurrogateBefore)
	{
		this.beenASurrogateBefore = beenASurrogateBefore;
	}

}
