package com.linkit.garsi.egg.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class EggFamilyHistory
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String famHistory;
	@Column(length = 50)
	private String famPsyIssues;
	@Column(length = 50)
	private String famGenHistory;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;
	
	@Column(length = 50)
	private String resourceId;
	@Transient
	private List<EggFamilyMember> familyMembers;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFamHistory() {
		return famHistory;
	}
	public void setFamHistory(String famHistory) {
		this.famHistory = famHistory;
	}
	public String getFamPsyIssues() {
		return famPsyIssues;
	}
	public void setFamPsyIssues(String famPsyIssues) {
		this.famPsyIssues = famPsyIssues;
	}
	public String getFamGenHistory() {
		return famGenHistory;
	}
	public void setFamGenHistory(String famGenHistory) {
		this.famGenHistory = famGenHistory;
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
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public List<EggFamilyMember> getFamilyMembers() {
		return familyMembers;
	}
	public void setFamilyMembers(List<EggFamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

}