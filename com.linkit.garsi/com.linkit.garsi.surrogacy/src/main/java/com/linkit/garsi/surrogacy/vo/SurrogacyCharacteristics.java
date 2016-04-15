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
 * Characteristics and Matters of Surrogacy
 * @author qlm
 *
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurrogacyCharacteristics implements IResource{

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	
	@Column(length = 50)
	private String resourceId;

	@Column(length = 50)
	private String personalityAndCharacter;

	@Column(length = 50)
	private String hobbies;

	@Column(length = 50)
	private String doInSpareTime;

	@Column(length = 50)
	private String philosophyOnLife;

	@Column(length = 50)
	private String whyWantToBeSurrogate;

	@Column(length = 50)
	private String howTheSurrogateProgramWorks;

	@Column(length = 50)
	private String  tellYourChildrenAboutSurrogate;

	@Column(length = 50)
	private String  mostImportantQualities;

	@Column(length = 50)
	private String  relationshipWithIntendedParents;

	@Column(length = 50)
	private String  wantToMeetRecipientCouple;

	@Column(length = 50)
	private String  maximumNumberOfEmbryos;

	@Column(length = 50)
	private String  carryTwins;

	@Column(length = 50)
	private String  carryTriplets;

	@Column(length = 50)
	private String  undergoSelectiveReductionProcedure;

	@Column(length = 50)
	private String  allowThemMakeDecision;

	@Column(length = 50)
	private String  understandThat;

	@Column(length = 50)
	private String  haveSurroundingFamilyAndHaveTheirSupport;
	
	
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

	public String getPersonalityAndCharacter() {
		return personalityAndCharacter;
	}

	public void setPersonalityAndCharacter(String personalityAndCharacter) {
		this.personalityAndCharacter = personalityAndCharacter;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getDoInSpareTime() {
		return doInSpareTime;
	}

	public void setDoInSpareTime(String doInSpareTime) {
		this.doInSpareTime = doInSpareTime;
	}

	public String getPhilosophyOnLife() {
		return philosophyOnLife;
	}

	public void setPhilosophyOnLife(String philosophyOnLife) {
		this.philosophyOnLife = philosophyOnLife;
	}

	public String getWhyWantToBeSurrogate() {
		return whyWantToBeSurrogate;
	}

	public void setWhyWantToBeSurrogate(String whyWantToBeSurrogate) {
		this.whyWantToBeSurrogate = whyWantToBeSurrogate;
	}

	public String getHowTheSurrogateProgramWorks() {
		return howTheSurrogateProgramWorks;
	}

	public void setHowTheSurrogateProgramWorks(String howTheSurrogateProgramWorks) {
		this.howTheSurrogateProgramWorks = howTheSurrogateProgramWorks;
	}

	public String getTellYourChildrenAboutSurrogate() {
		return tellYourChildrenAboutSurrogate;
	}

	public void setTellYourChildrenAboutSurrogate(
			String tellYourChildrenAboutSurrogate) {
		this.tellYourChildrenAboutSurrogate = tellYourChildrenAboutSurrogate;
	}

	public String getMostImportantQualities() {
		return mostImportantQualities;
	}

	public void setMostImportantQualities(String mostImportantQualities) {
		this.mostImportantQualities = mostImportantQualities;
	}

	public String getRelationshipWithIntendedParents() {
		return relationshipWithIntendedParents;
	}

	public void setRelationshipWithIntendedParents(
			String relationshipWithIntendedParents) {
		this.relationshipWithIntendedParents = relationshipWithIntendedParents;
	}

	public String getWantToMeetRecipientCouple() {
		return wantToMeetRecipientCouple;
	}

	public void setWantToMeetRecipientCouple(String wantToMeetRecipientCouple) {
		this.wantToMeetRecipientCouple = wantToMeetRecipientCouple;
	}

	public String getMaximumNumberOfEmbryos() {
		return maximumNumberOfEmbryos;
	}

	public void setMaximumNumberOfEmbryos(String maximumNumberOfEmbryos) {
		this.maximumNumberOfEmbryos = maximumNumberOfEmbryos;
	}

	public String getCarryTwins() {
		return carryTwins;
	}

	public void setCarryTwins(String carryTwins) {
		this.carryTwins = carryTwins;
	}

	public String getCarryTriplets() {
		return carryTriplets;
	}

	public void setCarryTriplets(String carryTriplets) {
		this.carryTriplets = carryTriplets;
	}

	public String getUndergoSelectiveReductionProcedure() {
		return undergoSelectiveReductionProcedure;
	}

	public void setUndergoSelectiveReductionProcedure(
			String undergoSelectiveReductionProcedure) {
		this.undergoSelectiveReductionProcedure = undergoSelectiveReductionProcedure;
	}

	public String getAllowThemMakeDecision() {
		return allowThemMakeDecision;
	}

	public void setAllowThemMakeDecision(String allowThemMakeDecision) {
		this.allowThemMakeDecision = allowThemMakeDecision;
	}

	public String getUnderstandThat() {
		return understandThat;
	}

	public void setUnderstandThat(String understandThat) {
		this.understandThat = understandThat;
	}

	public String getHaveSurroundingFamilyAndHaveTheirSupport() {
		return haveSurroundingFamilyAndHaveTheirSupport;
	}

	public void setHaveSurroundingFamilyAndHaveTheirSupport(
			String haveSurroundingFamilyAndHaveTheirSupport) {
		this.haveSurroundingFamilyAndHaveTheirSupport = haveSurroundingFamilyAndHaveTheirSupport;
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
