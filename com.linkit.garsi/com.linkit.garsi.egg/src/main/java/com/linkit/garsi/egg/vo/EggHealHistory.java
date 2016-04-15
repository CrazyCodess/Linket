package com.linkit.garsi.egg.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 健康历史
 * 
 * @author Administrator
 * 
 */
@Entity
@Table
public class EggHealHistory
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String resourceId;
	@Column(length = 50)
	private String medicalProblem;
	@Column(length = 50)
	private String phyProblem;
	/**
	 * 受伤历史
	 */
	@Column(length = 50)
	private String injury;
	/**
	 * 过敏
	 */
	@Column(length = 50)
	private String allergy;
	/**
	 * 酒
	 */
	@Column(length = 50)
	private String alcohol;
	@Column(length = 50)
	private String smoke;
	/**
	 * 运动
	 */
	@Column(length = 50)
	private String exercise;
	/**
	 * 睡眠
	 */
	@Column(length = 50)
	private String sleepPattern;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}


	public String getMedicalProblem()
	{
		return medicalProblem;
	}

	public void setMedicalProblem(String medicalProblem)
	{
		this.medicalProblem = medicalProblem;
	}

	public String getPhyProblem()
	{
		return phyProblem;
	}

	public void setPhyProblem(String phyProblem)
	{
		this.phyProblem = phyProblem;
	}

	public String getInjury()
	{
		return injury;
	}

	public void setInjury(String injury)
	{
		this.injury = injury;
	}

	public String getAllergy()
	{
		return allergy;
	}

	public void setAllergy(String allergy)
	{
		this.allergy = allergy;
	}

	public String getAlcohol()
	{
		return alcohol;
	}

	public void setAlcohol(String alcohol)
	{
		this.alcohol = alcohol;
	}

	public String getSmoke()
	{
		return smoke;
	}

	public void setSmoke(String smoke)
	{
		this.smoke = smoke;
	}

	public String getExercise()
	{
		return exercise;
	}

	public void setExercise(String exercise)
	{
		this.exercise = exercise;
	}

	public String getSleepPattern()
	{
		return sleepPattern;
	}

	public void setSleepPattern(String sleepPattern)
	{
		this.sleepPattern = sleepPattern;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
