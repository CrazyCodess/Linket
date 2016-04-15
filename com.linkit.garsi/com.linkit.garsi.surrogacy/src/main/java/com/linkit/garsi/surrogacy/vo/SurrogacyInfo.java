package com.linkit.garsi.surrogacy.vo;

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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.linkit.garsi.common.IResource;
import com.linkit.garsi.common.ResourceType;

/**
 * 代母个人基本信息表
 * 
 * @author qlm
 * 
 */
@Entity
@Table
public class SurrogacyInfo implements IResource
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String middleName;
	@Column(length = 20)
	private String lastName;
	@Column
	private int age;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthdate;
	@Column
	private float height;
	@Column
	private float weight;
	@Column(length = 22)
	private String bmi;
	@Column(length = 22)
	private String phone;
	@Column(length = 30)
	private String email;
	@Column(length = 30)
	private String skypeID;
	@Column(length = 50)
	private String streetAddress;
	@Column(length = 50)
	private String city;
	@Column(length = 50)
	private String state;
	@Column(length = 50)
	private String zipCode;
	@Column(length = 50)
	private String occupation;
	@Column(length = 50)
	private String beenASurrogateBefore;
	@Column(length = 50)
	private String married;
	@Column(length = 50)
	private String relationshipStatus;
	@Column(length = 50)
	private String howLong;
	@Column(length = 50)
	private String spouseOccupation;
	@Column(length = 50)
	private String ethnicity;
	@Column(length = 50)
	private String citizenship;
	@Column(length = 50)
	private String nationalOrigin;
	@Column(length = 50)
	private String fathersMotherAncestry;
	@Column(length = 50)
	private String fathersFatherAncestry;
	@Column(length = 50)
	private String mothersMotherAncestry;
	@Column(length = 50)
	private String mothersFatherAncestry;
	@Column(length = 50)
	private String childrenInfo;
	@Column(length = 50)
	private String religionAtBirth;
	@Column(length = 50)
	private String practicingReligion;
	@Column(length = 50)
	private String haveTattoos;
	@Column(length = 50)
	private String numberOfTattoos;
	@Column(length = 50)
	private String havePiercings;
	@Column(length = 50)
	private String numberOfPiercings;
	@Column(length = 50)
	private String otherFeatures;
	@Column(length = 50)
	private String dateOfMarriage;
	@Column(length = 50)
	private String numberOfDivorces;
	@Column(length = 50)
	private String dateOfDivorce;
	@Column(length = 50)
	private String yearInRelationship;
	@Column(length = 50)
	private String liveWithPartner;
	@Column(length = 50)
	private String isPartnerChildrensFather;
	@Column(length = 50)
	private String allChildrenInCustody;
	@Column(length = 50)
	private String filedForBankruptcy;
	@Column(length = 50)
	private String psychiatricFacility;
	@Column(length = 50)
	private String beenArrested;
	@Column(length = 50)
	private String beenInvolved;
	@Column(length = 50)
	private String existingRestrainingOrders;
	@Column(length = 50)
	private String inSubstanceAbuseProgram;
	@Column(length = 50)
	private String drive;
	@Column(length = 50)
	private String ownCar;
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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public Date getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
	{
		this.weight = weight;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSkypeID()
	{
		return skypeID;
	}

	public void setSkypeID(String skypeID)
	{
		this.skypeID = skypeID;
	}

	public String getStreetAddress()
	{
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
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

	public String getMarried()
	{
		return married;
	}

	public void setMarried(String married)
	{
		this.married = married;
	}

	public String getRelationshipStatus()
	{
		return relationshipStatus;
	}

	public void setRelationshipStatus(String relationshipStatus)
	{
		this.relationshipStatus = relationshipStatus;
	}

	public String getHowLong()
	{
		return howLong;
	}

	public void setHowLong(String howLong)
	{
		this.howLong = howLong;
	}

	public String getSpouseOccupation()
	{
		return spouseOccupation;
	}

	public void setSpouseOccupation(String spouseOccupation)
	{
		this.spouseOccupation = spouseOccupation;
	}

	public String getEthnicity()
	{
		return ethnicity;
	}

	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
	}

	public String getCitizenship()
	{
		return citizenship;
	}

	public void setCitizenship(String citizenship)
	{
		this.citizenship = citizenship;
	}

	public String getNationalOrigin()
	{
		return nationalOrigin;
	}

	public void setNationalOrigin(String nationalOrigin)
	{
		this.nationalOrigin = nationalOrigin;
	}

	public String getFathersMotherAncestry()
	{
		return fathersMotherAncestry;
	}

	public void setFathersMotherAncestry(String fathersMotherAncestry)
	{
		this.fathersMotherAncestry = fathersMotherAncestry;
	}

	public String getFathersFatherAncestry()
	{
		return fathersFatherAncestry;
	}

	public void setFathersFatherAncestry(String fathersFatherAncestry)
	{
		this.fathersFatherAncestry = fathersFatherAncestry;
	}

	public String getMothersMotherAncestry()
	{
		return mothersMotherAncestry;
	}

	public void setMothersMotherAncestry(String mothersMotherAncestry)
	{
		this.mothersMotherAncestry = mothersMotherAncestry;
	}

	public String getMothersFatherAncestry()
	{
		return mothersFatherAncestry;
	}

	public void setMothersFatherAncestry(String mothersFatherAncestry)
	{
		this.mothersFatherAncestry = mothersFatherAncestry;
	}

	public String getChildrenInfo()
	{
		return childrenInfo;
	}

	public void setChildrenInfo(String childrenInfo)
	{
		this.childrenInfo = childrenInfo;
	}

	public String getReligionAtBirth()
	{
		return religionAtBirth;
	}

	public void setReligionAtBirth(String religionAtBirth)
	{
		this.religionAtBirth = religionAtBirth;
	}

	public String getPracticingReligion()
	{
		return practicingReligion;
	}

	public void setPracticingReligion(String practicingReligion)
	{
		this.practicingReligion = practicingReligion;
	}

	public String getHaveTattoos()
	{
		return haveTattoos;
	}

	public void setHaveTattoos(String haveTattoos)
	{
		this.haveTattoos = haveTattoos;
	}

	public String getNumberOfTattoos()
	{
		return numberOfTattoos;
	}

	public void setNumberOfTattoos(String numberOfTattoos)
	{
		this.numberOfTattoos = numberOfTattoos;
	}

	public String getHavePiercings()
	{
		return havePiercings;
	}

	public void setHavePiercings(String havePiercings)
	{
		this.havePiercings = havePiercings;
	}

	public String getNumberOfPiercings()
	{
		return numberOfPiercings;
	}

	public void setNumberOfPiercings(String numberOfPiercings)
	{
		this.numberOfPiercings = numberOfPiercings;
	}

	public String getOtherFeatures()
	{
		return otherFeatures;
	}

	public void setOtherFeatures(String otherFeatures)
	{
		this.otherFeatures = otherFeatures;
	}

	public String getDateOfMarriage()
	{
		return dateOfMarriage;
	}

	public void setDateOfMarriage(String dateOfMarriage)
	{
		this.dateOfMarriage = dateOfMarriage;
	}

	public String getNumberOfDivorces()
	{
		return numberOfDivorces;
	}

	public void setNumberOfDivorces(String numberOfDivorces)
	{
		this.numberOfDivorces = numberOfDivorces;
	}

	public String getDateOfDivorce()
	{
		return dateOfDivorce;
	}

	public void setDateOfDivorce(String dateOfDivorce)
	{
		this.dateOfDivorce = dateOfDivorce;
	}

	public String getYearInRelationship()
	{
		return yearInRelationship;
	}

	public void setYearInRelationship(String yearInRelationship)
	{
		this.yearInRelationship = yearInRelationship;
	}

	public String getLiveWithPartner()
	{
		return liveWithPartner;
	}

	public void setLiveWithPartner(String liveWithPartner)
	{
		this.liveWithPartner = liveWithPartner;
	}

	public String getIsPartnerChildrensFather()
	{
		return isPartnerChildrensFather;
	}

	public void setIsPartnerChildrensFather(String isPartnerChildrensFather)
	{
		this.isPartnerChildrensFather = isPartnerChildrensFather;
	}

	public String getAllChildrenInCustody()
	{
		return allChildrenInCustody;
	}

	public void setAllChildrenInCustody(String allChildrenInCustody)
	{
		this.allChildrenInCustody = allChildrenInCustody;
	}

	public String getFiledForBankruptcy()
	{
		return filedForBankruptcy;
	}

	public void setFiledForBankruptcy(String filedForBankruptcy)
	{
		this.filedForBankruptcy = filedForBankruptcy;
	}

	public String getPsychiatricFacility()
	{
		return psychiatricFacility;
	}

	public void setPsychiatricFacility(String psychiatricFacility)
	{
		this.psychiatricFacility = psychiatricFacility;
	}

	public String getBeenArrested()
	{
		return beenArrested;
	}

	public void setBeenArrested(String beenArrested)
	{
		this.beenArrested = beenArrested;
	}

	public String getBeenInvolved()
	{
		return beenInvolved;
	}

	public void setBeenInvolved(String beenInvolved)
	{
		this.beenInvolved = beenInvolved;
	}

	public String getExistingRestrainingOrders()
	{
		return existingRestrainingOrders;
	}

	public void setExistingRestrainingOrders(String existingRestrainingOrders)
	{
		this.existingRestrainingOrders = existingRestrainingOrders;
	}

	public String getInSubstanceAbuseProgram()
	{
		return inSubstanceAbuseProgram;
	}

	public void setInSubstanceAbuseProgram(String inSubstanceAbuseProgram)
	{
		this.inSubstanceAbuseProgram = inSubstanceAbuseProgram;
	}

	public String getDrive()
	{
		return drive;
	}

	public void setDrive(String drive)
	{
		this.drive = drive;
	}

	public String getOwnCar()
	{
		return ownCar;
	}

	public void setOwnCar(String ownCar)
	{
		this.ownCar = ownCar;
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

	@JsonGetter("BMI")
	public String getBmi()
	{
		return bmi;
	}

	@JsonSetter("BMI")
	public void setBmi(String bmi)
	{
		this.bmi = bmi;
	}

	@Override
	public String getTitle()
	{
		return this.lastName + " " + DateFormatUtils.format(this.birthdate, "yyyy-MM-dd") + " " + nationalOrigin;
	}

	@Override
	public String getResourceType()
	{
		return ResourceType.SURROGACY;
	}

}
