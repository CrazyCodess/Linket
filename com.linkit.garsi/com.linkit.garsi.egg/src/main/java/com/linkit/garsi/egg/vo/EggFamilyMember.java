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
public class EggFamilyMember {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	/**
	 * Mother,Father,Sibling 
	 */
	@Column(length = 50)
	private String memberRelation;
	@Column(length = 50)
	private Integer age;
	@Column(length = 50)
	private String ethnicOrigin;
	@Column
	private Double height;
	@Column
	private Double weight;
	@Column(length = 50)
	private String eyeColor;
	@Column(length = 50)
	private String hairColor;
	/**
	 * Excellent/Good, Fair/Poor 
	 */
	@Column(length = 50)
	private String healthStatus;
	@Column
	private Integer deceaseAge;
	@Column(length = 200)
	private String deceaseReson;
	@Column(length = 50)
	private String familyHistoryId;
	@Column(length = 50)
	private String resourceId;
	//排序
	@Column
	private Integer sortNum;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberRelation() {
		return memberRelation;
	}
	public void setMemberRelation(String memberRelation) {
		this.memberRelation = memberRelation;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEthnicOrigin() {
		return ethnicOrigin;
	}
	public void setEthnicOrigin(String ethnicOrigin) {
		this.ethnicOrigin = ethnicOrigin;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public Integer getDeceaseAge() {
		return deceaseAge;
	}
	public void setDeceaseAge(Integer deceaseAge) {
		this.deceaseAge = deceaseAge;
	}
	public String getDeceaseReson() {
		return deceaseReson;
	}
	public void setDeceaseReson(String deceaseReson) {
		this.deceaseReson = deceaseReson;
	}
	public String getFamilyHistoryId() {
		return familyHistoryId;
	}
	public void setFamilyHistoryId(String familyHistoryId) {
		this.familyHistoryId = familyHistoryId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
}
