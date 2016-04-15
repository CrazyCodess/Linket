package com.linkit.garsi.surrogacy.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkit.garsi.common.IResource;
import com.linkit.garsi.common.ResourceType;

/**
 * 代母生理信息表
 * 
 * @author qlm
 * 
 */
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurrogacyMedicalInfo implements IResource
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;

	@Column(length = 50)
	private String resourceId;

	@Column(length = 50)
	private String adopted;

	@Column(length = 50)
	private String bloodType;

	@Column(length = 50)
	private String sexualOrientation;

	@Column(length = 50)
	private String numberOfPregnancies;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfLastOBVisit;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfLastPapSmear;

	@Column(length = 50)
	private String weightChanged;

	@Column(length = 50)
	private String usingBirthControl;

	@Column(length = 50)
	private String whatMethod;

	@Column(length = 50)
	private String forHowLong;

	@Column(length = 50)
	private String anyOtherBirthControlMethod;

	@Column(length = 50)
	private String smoke;

	@Column(length = 50)
	private String drink;

	@Column(length = 50)
	private String everIllicitDrugs;

	@Column(length = 50)
	private String currentlyIllicitDrugs;

	@Column(length = 50)
	private String listDrugs;

	@Column(length = 50)
	private String regularMonthlyMenstrualCycles;

	@Column(length = 50)
	private String details;

	@Column(length = 50)
	private String difficultyPregnancy;

	@Column(length = 50)
	private String twinsTriplets;

	@Column(length = 50)
	private String hadTransfusion;

	@Column(length = 50)
	private String hadAnyPsychologicalCounseling;

	@Column(length = 50)
	private String prescribedMedicationTreatBehavioralCondition;

	@Column(length = 50)
	private String diagnosedWithPsychologicalDisorder;

	@Column(length = 50)
	private String hospitalizedFoPsychiatricIllness;

	@Column(length = 50)
	private String sexuallyActiveWithAnyoneOther;

	@Column(length = 50)
	private String physicallyAbused;

	@Column(length = 50)
	private String sexuallyAbused;

	@Column(length = 50)
	private String thoughtsAttemptedSuicide;

	@Column(length = 50)
	private String medicalProblems;

	@Column(length = 50)
	private String medicationsAndDosageCurrently;

	@Column(length = 50)
	private String medicationAndDosageLastYearAndReason;

	@Column(length = 50)
	private String hospitalizationsSurgeriesPlasticSurgeries;

	@Column(length = 50)
	private String HIVOrAIDS;

	@Column(length = 10)
	private String diagnosedWithTheFollowing1;
	
	@Column(length = 10)
	private String diagnosedWithTheFollowing2;
	
	@Column(length = 10)
	private String diagnosedWithTheFollowing3;
	
	@Column(length = 50)
	private String explainAnyTreatments;

	@Column(length = 50)
	private String lastTestedForSTDs;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getAdopted()
	{
		return adopted;
	}

	public void setAdopted(String adopted)
	{
		this.adopted = adopted;
	}

	public String getBloodType()
	{
		return bloodType;
	}

	public void setBloodType(String bloodType)
	{
		this.bloodType = bloodType;
	}

	public String getSexualOrientation()
	{
		return sexualOrientation;
	}

	public void setSexualOrientation(String sexualOrientation)
	{
		this.sexualOrientation = sexualOrientation;
	}

	public Date getDateOfLastOBVisit()
	{
		return dateOfLastOBVisit;
	}

	public void setDateOfLastOBVisit(Date dateOfLastOBVisit)
	{
		this.dateOfLastOBVisit = dateOfLastOBVisit;
	}

	public String getWeightChanged()
	{
		return weightChanged;
	}

	public void setWeightChanged(String weightChanged)
	{
		this.weightChanged = weightChanged;
	}

	public String getUsingBirthControl()
	{
		return usingBirthControl;
	}

	public void setUsingBirthControl(String usingBirthControl)
	{
		this.usingBirthControl = usingBirthControl;
	}

	public String getWhatMethod()
	{
		return whatMethod;
	}

	public void setWhatMethod(String whatMethod)
	{
		this.whatMethod = whatMethod;
	}

	public String getForHowLong()
	{
		return forHowLong;
	}

	public void setForHowLong(String forHowLong)
	{
		this.forHowLong = forHowLong;
	}

	public String getAnyOtherBirthControlMethod()
	{
		return anyOtherBirthControlMethod;
	}

	public void setAnyOtherBirthControlMethod(String anyOtherBirthControlMethod)
	{
		this.anyOtherBirthControlMethod = anyOtherBirthControlMethod;
	}

	public String getSmoke()
	{
		return smoke;
	}

	public void setSmoke(String smoke)
	{
		this.smoke = smoke;
	}

	public String getDrink()
	{
		return drink;
	}

	public void setDrink(String drink)
	{
		this.drink = drink;
	}

	public String getEverIllicitDrugs()
	{
		return everIllicitDrugs;
	}

	public void setEverIllicitDrugs(String everIllicitDrugs)
	{
		this.everIllicitDrugs = everIllicitDrugs;
	}

	public String getCurrentlyIllicitDrugs()
	{
		return currentlyIllicitDrugs;
	}

	public void setCurrentlyIllicitDrugs(String currentlyIllicitDrugs)
	{
		this.currentlyIllicitDrugs = currentlyIllicitDrugs;
	}

	public String getListDrugs()
	{
		return listDrugs;
	}

	public void setListDrugs(String listDrugs)
	{
		this.listDrugs = listDrugs;
	}

	public String getRegularMonthlyMenstrualCycles()
	{
		return regularMonthlyMenstrualCycles;
	}

	public void setRegularMonthlyMenstrualCycles(String regularMonthlyMenstrualCycles)
	{
		this.regularMonthlyMenstrualCycles = regularMonthlyMenstrualCycles;
	}

	public String getDetails()
	{
		return details;
	}

	public void setDetails(String details)
	{
		this.details = details;
	}

	public String getDifficultyPregnancy()
	{
		return difficultyPregnancy;
	}

	public void setDifficultyPregnancy(String difficultyPregnancy)
	{
		this.difficultyPregnancy = difficultyPregnancy;
	}

	public String getTwinsTriplets()
	{
		return twinsTriplets;
	}

	public void setTwinsTriplets(String twinsTriplets)
	{
		this.twinsTriplets = twinsTriplets;
	}

	public String getHadTransfusion()
	{
		return hadTransfusion;
	}

	public void setHadTransfusion(String hadTransfusion)
	{
		this.hadTransfusion = hadTransfusion;
	}

	public String getHadAnyPsychologicalCounseling()
	{
		return hadAnyPsychologicalCounseling;
	}

	public void setHadAnyPsychologicalCounseling(String hadAnyPsychologicalCounseling)
	{
		this.hadAnyPsychologicalCounseling = hadAnyPsychologicalCounseling;
	}

	public String getPrescribedMedicationTreatBehavioralCondition()
	{
		return prescribedMedicationTreatBehavioralCondition;
	}

	public void setPrescribedMedicationTreatBehavioralCondition(String prescribedMedicationTreatBehavioralCondition)
	{
		this.prescribedMedicationTreatBehavioralCondition = prescribedMedicationTreatBehavioralCondition;
	}

	public String getDiagnosedWithPsychologicalDisorder()
	{
		return diagnosedWithPsychologicalDisorder;
	}

	public void setDiagnosedWithPsychologicalDisorder(String diagnosedWithPsychologicalDisorder)
	{
		this.diagnosedWithPsychologicalDisorder = diagnosedWithPsychologicalDisorder;
	}

	public String getHospitalizedFoPsychiatricIllness()
	{
		return hospitalizedFoPsychiatricIllness;
	}

	public void setHospitalizedFoPsychiatricIllness(String hospitalizedFoPsychiatricIllness)
	{
		this.hospitalizedFoPsychiatricIllness = hospitalizedFoPsychiatricIllness;
	}

	public String getSexuallyActiveWithAnyoneOther()
	{
		return sexuallyActiveWithAnyoneOther;
	}

	public void setSexuallyActiveWithAnyoneOther(String sexuallyActiveWithAnyoneOther)
	{
		this.sexuallyActiveWithAnyoneOther = sexuallyActiveWithAnyoneOther;
	}

	public String getPhysicallyAbused()
	{
		return physicallyAbused;
	}

	public void setPhysicallyAbused(String physicallyAbused)
	{
		this.physicallyAbused = physicallyAbused;
	}

	public String getSexuallyAbused()
	{
		return sexuallyAbused;
	}

	public void setSexuallyAbused(String sexuallyAbused)
	{
		this.sexuallyAbused = sexuallyAbused;
	}

	public String getThoughtsAttemptedSuicide()
	{
		return thoughtsAttemptedSuicide;
	}

	public void setThoughtsAttemptedSuicide(String thoughtsAttemptedSuicide)
	{
		this.thoughtsAttemptedSuicide = thoughtsAttemptedSuicide;
	}

	public String getMedicalProblems()
	{
		return medicalProblems;
	}

	public void setMedicalProblems(String medicalProblems)
	{
		this.medicalProblems = medicalProblems;
	}

	public String getMedicationsAndDosageCurrently()
	{
		return medicationsAndDosageCurrently;
	}

	public void setMedicationsAndDosageCurrently(String medicationsAndDosageCurrently)
	{
		this.medicationsAndDosageCurrently = medicationsAndDosageCurrently;
	}

	public String getMedicationAndDosageLastYearAndReason()
	{
		return medicationAndDosageLastYearAndReason;
	}

	public void setMedicationAndDosageLastYearAndReason(String medicationAndDosageLastYearAndReason)
	{
		this.medicationAndDosageLastYearAndReason = medicationAndDosageLastYearAndReason;
	}

	public String getHospitalizationsSurgeriesPlasticSurgeries()
	{
		return hospitalizationsSurgeriesPlasticSurgeries;
	}

	public void setHospitalizationsSurgeriesPlasticSurgeries(String hospitalizationsSurgeriesPlasticSurgeries)
	{
		this.hospitalizationsSurgeriesPlasticSurgeries = hospitalizationsSurgeriesPlasticSurgeries;
	}

	public String getHIVOrAIDS()
	{
		return HIVOrAIDS;
	}

	public void setHIVOrAIDS(String hIVOrAIDS)
	{
		HIVOrAIDS = hIVOrAIDS;
	}
	
	public String getDiagnosedWithTheFollowing1() {
		return diagnosedWithTheFollowing1;
	}

	public void setDiagnosedWithTheFollowing1(String diagnosedWithTheFollowing1) {
		this.diagnosedWithTheFollowing1 = diagnosedWithTheFollowing1;
	}

	public String getDiagnosedWithTheFollowing2() {
		return diagnosedWithTheFollowing2;
	}

	public void setDiagnosedWithTheFollowing2(String diagnosedWithTheFollowing2) {
		this.diagnosedWithTheFollowing2 = diagnosedWithTheFollowing2;
	}

	public String getDiagnosedWithTheFollowing3() {
		return diagnosedWithTheFollowing3;
	}

	public void setDiagnosedWithTheFollowing3(String diagnosedWithTheFollowing3) {
		this.diagnosedWithTheFollowing3 = diagnosedWithTheFollowing3;
	}

	public String getExplainAnyTreatments() {
		return explainAnyTreatments;
	}

	public void setExplainAnyTreatments(String explainAnyTreatments) {
		this.explainAnyTreatments = explainAnyTreatments;
	}

	public String getLastTestedForSTDs()
	{
		return lastTestedForSTDs;
	}

	public void setLastTestedForSTDs(String lastTestedForSTDs)
	{
		this.lastTestedForSTDs = lastTestedForSTDs;
	}

	@Override
	public String getTitle()
	{
		return "Resource Id:" + this.resourceId;
	}

	@Override
	public String getResourceType()
	{
		return ResourceType.SURROGACY;
	}

	public String getNumberOfPregnancies()
	{
		return numberOfPregnancies;
	}

	public void setNumberOfPregnancies(String numberOfPregnancies)
	{
		this.numberOfPregnancies = numberOfPregnancies;
	}

	public Date getDateOfLastPapSmear()
	{
		return dateOfLastPapSmear;
	}

	public void setDateOfLastPapSmear(Date dateOfLastPapSmear)
	{
		this.dateOfLastPapSmear = dateOfLastPapSmear;
	}

}
