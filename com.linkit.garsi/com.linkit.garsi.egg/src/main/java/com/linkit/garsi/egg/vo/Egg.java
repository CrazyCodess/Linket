package com.linkit.garsi.egg.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.linkit.garsi.common.IResource;
import com.linkit.garsi.common.ResourceType;

@Entity
@Table
public class Egg  implements IResource
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String fullName;
	
	@Column(length = 50)
	private String middleName;
	@Column(length = 50)
	private String lastName;
	
	@Column(length = 50)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthdate;
	@Column
	private Integer age;
	@Column(length = 50)
	private String bloodType;
	@Column(length = 50)
	private String race;
	@Column(length = 50)
	private String ethnicOrigin;
	@Column
	private Double height; 
	@Column(length = 20)
	private String heightUnit;
	@Column
	private Double weight;
	@Column(length = 20)
	private String weightUnit;
	@Column(length = 50)
	private String bmi;
	@Column(length = 50)
	private String skinTone;
	@Column(length = 50)
	private String skinType;
	@Column(length = 50)
	private String eyeColor;
	@Column(length = 50)
	private String hairColor;
	@Column(length = 50)
	private String hairType;
	@Column(length = 50)
	private String hairTexture;
	@Column(length = 50)
	private String bodyType;
	@Column(length = 50)
	private String nativeTongue;
	@Column(length = 50)
	private String maritalStatus;
	@Column(length = 50)
	private String drive;
	@Column(length = 50)
	private String compenstation;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	@Transient
	private String processState;
	@Transient
	private String resourceState;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getBloodType()
	{
		return bloodType;
	}

	public void setBloodType(String bloodType)
	{
		this.bloodType = bloodType;
	}

	public String getRace()
	{
		return race;
	}

	public void setRace(String race)
	{
		this.race = race;
	}

	public String getEthnicOrigin()
	{
		return ethnicOrigin;
	}

	public void setEthnicOrigin(String ethnicOrigin)
	{
		this.ethnicOrigin = ethnicOrigin;
	}

	public String getSkinTone()
	{
		return skinTone;
	}

	public void setSkinTone(String skinTone)
	{
		this.skinTone = skinTone;
	}

	public String getSkinType()
	{
		return skinType;
	}

	public void setSkinType(String skinType)
	{
		this.skinType = skinType;
	}

	public String getEyeColor()
	{
		return eyeColor;
	}

	public void setEyeColor(String eyeColor)
	{
		this.eyeColor = eyeColor;
	}

	public String getHairColor()
	{
		return hairColor;
	}

	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}

	public String getHairType()
	{
		return hairType;
	}

	public void setHairType(String hairType)
	{
		this.hairType = hairType;
	}

	public String getHairTexture()
	{
		return hairTexture;
	}

	public void setHairTexture(String hairTexture)
	{
		this.hairTexture = hairTexture;
	}

	public String getBodyType()
	{
		return bodyType;
	}

	public void setBodyType(String bodyType)
	{
		this.bodyType = bodyType;
	}

	public String getNativeTongue()
	{
		return nativeTongue;
	}

	public void setNativeTongue(String nativeTongue)
	{
		this.nativeTongue = nativeTongue;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	public String getDrive()
	{
		return drive;
	}

	public void setDrive(String drive)
	{
		this.drive = drive;
	}

	public String getCompenstation()
	{
		return compenstation;
	}

	public void setCompenstation(String compenstation)
	{
		this.compenstation = compenstation;
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

	public Double getHeight()
	{
		return height;
	}

	public void setHeight(Double height)
	{
		this.height = height;
	}

	public Double getWeight()
	{
		return weight;
	}

	public void setWeight(Double weight)
	{
		this.weight = weight;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	@Override
	public String getTitle()
	{
		return  this.id+ "" +DateFormatUtils.format(this.birthdate, "yyyy-MM-dd") + " " + ethnicOrigin;
	}

	@Override
	public String getResourceType()
	{
		return ResourceType.EGG;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	public String getResourceState() {
		return resourceState;
	}

	public void setResourceState(String resourceState) {
		this.resourceState = resourceState;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHeightUnit() {
		return heightUnit;
	}

	public void setHeightUnit(String heightUnit) {
		this.heightUnit = heightUnit;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

}
