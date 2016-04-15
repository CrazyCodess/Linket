package com.linkit.garsi.egg.vo;


public class EggForm {
	private Integer ageStart;
	private Integer ageEnd;
	private Double heightStart;
	private Double heightEnd;

	private Double weightStart;
	private Double weightEnd;

	private String bmi;

	private String race;

	// 职业
	private String occupation;
	
	//国籍
	private String nationality;
	
	private String creatorId;
	
	private String customerId;

	private String processState;
	
	private String resourceState;
	
	
	
	public String getResourceState() {
		return resourceState;
	}

	public void setResourceState(String resourceState) {
		this.resourceState = resourceState;
	}

	public Integer getAgeStart() {
		return ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
	}

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
	}

	public Double getHeightStart() {
		return heightStart;
	}

	public void setHeightStart(Double heightStart) {
		this.heightStart = heightStart;
	}

	public Double getHeightEnd() {
		return heightEnd;
	}

	public void setHeightEnd(Double heightEnd) {
		this.heightEnd = heightEnd;
	}

	public Double getWeightStart() {
		return weightStart;
	}

	public void setWeightStart(Double weightStart) {
		this.weightStart = weightStart;
	}

	public Double getWeightEnd() {
		return weightEnd;
	}

	public void setWeightEnd(Double weightEnd) {
		this.weightEnd = weightEnd;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProcessState() {
		return processState;
	}

	public void setProcessState(String processState) {
		this.processState = processState;
	}

	
}
