package com.linkit.garsi.surrogacy.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkit.garsi.common.IResource;
import com.linkit.garsi.common.ResourceType;

/**
 * 代码生活习惯表
 * @author qlm
 *
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurrogacyLifeStyle implements  IResource{

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	
	@Column(length = 50)
	private String resourceId;
	
	@Column(length = 50)
	private String currentEmploymentPosition;
	
	@Column(length = 50)
	private String bestSubjectsInSchool;
	
	@Column(length = 50)
	private String highestLevelOfSchoolCompleted;
	
	@Column(length = 50)
	private String plansOnFurthering;
	
	@Column(length = 50)
	private String goals;
	
	@Column(length = 50)
	private String healthInsurance;
	
	@Column(length = 50)
	private String languageOtherThanEnglish;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getCurrentEmploymentPosition() {
		return currentEmploymentPosition;
	}

	public void setCurrentEmploymentPosition(String currentEmploymentPosition) {
		this.currentEmploymentPosition = currentEmploymentPosition;
	}

	public String getBestSubjectsInSchool() {
		return bestSubjectsInSchool;
	}

	public void setBestSubjectsInSchool(String bestSubjectsInSchool) {
		this.bestSubjectsInSchool = bestSubjectsInSchool;
	}

	public String getHighestLevelOfSchoolCompleted() {
		return highestLevelOfSchoolCompleted;
	}

	public void setHighestLevelOfSchoolCompleted(
			String highestLevelOfSchoolCompleted) {
		this.highestLevelOfSchoolCompleted = highestLevelOfSchoolCompleted;
	}

	public String getPlansOnFurthering() {
		return plansOnFurthering;
	}

	public void setPlansOnFurthering(String plansOnFurthering) {
		this.plansOnFurthering = plansOnFurthering;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getLanguageOtherThanEnglish() {
		return languageOtherThanEnglish;
	}

	public void setLanguageOtherThanEnglish(String languageOtherThanEnglish) {
		this.languageOtherThanEnglish = languageOtherThanEnglish;
	}

	@Override
	public String getTitle() {
		return "Resource Id:"+this.resourceId;
	}

	@Override
	public String getResourceType() {
		return ResourceType.SURROGACY;
	}
	
}
