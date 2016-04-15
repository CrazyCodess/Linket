package com.linkit.garsi.egg.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class EggEduInfo
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String fullEdu;
	@Column(length = 50)
	private String school;
	@Column(length = 50)
	private String university;
	@Column(length = 50)
	private String gpa;
	@Column(length = 50)
	private String major;
	@Column(length = 50)
	private String language;
	@Column(length = 50)
	private String fluency;
	//职业
	@Column(length = 50)
	private String occupation;
	
	@Column(length = 50)
	private String resourceId;
	
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

	public String getFullEdu()
	{
		return fullEdu;
	}

	public void setFullEdu(String fullEdu)
	{
		this.fullEdu = fullEdu;
	}

	public String getSchool()
	{
		return school;
	}

	public void setSchool(String school)
	{
		this.school = school;
	}

	public String getUniversity()
	{
		return university;
	}

	public void setUniversity(String university)
	{
		this.university = university;
	}

	public String getGpa()
	{
		return gpa;
	}

	public void setGpa(String gpa)
	{
		this.gpa = gpa;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getFluency()
	{
		return fluency;
	}

	public void setFluency(String fluency)
	{
		this.fluency = fluency;
	}

	public String getOccupation()
	{
		return occupation;
	}

	public void setOccupation(String occupation)
	{
		this.occupation = occupation;
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