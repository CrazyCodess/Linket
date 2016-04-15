package com.linkit.garsi.egg.vo;


public class EggAsForm {
	private String askId;
	private String isDonate;// yes,no
	private String contactType;// Keep anonymous;Meet/talk to them

	private String [] recordIds;
	private String[] donateDate;

	private String[]  place;

	private String[]  retrievedEgg;

	private String[]  matureEgg;

	private String[]  fertilizeEgg;
	private String[] pregnancy;

	
	private String resourceId;

	public String getIsDonate() {
		return isDonate;
	}

	public void setIsDonate(String isDonate) {
		this.isDonate = isDonate;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	public String[] getDonateDate() {
		return donateDate;
	}

	public void setDonateDate(String[] donateDate) {
		this.donateDate = donateDate;
	}

	public String[] getPlace() {
		return place;
	}

	public void setPlace(String[] place) {
		this.place = place;
	}

	public String[] getRetrievedEgg() {
		return retrievedEgg;
	}

	public void setRetrievedEgg(String[] retrievedEgg) {
		this.retrievedEgg = retrievedEgg;
	}

	public String[] getMatureEgg() {
		return matureEgg;
	}

	public void setMatureEgg(String[] matureEgg) {
		this.matureEgg = matureEgg;
	}

	public String[] getFertilizeEgg() {
		return fertilizeEgg;
	}

	public void setFertilizeEgg(String[] fertilizeEgg) {
		this.fertilizeEgg = fertilizeEgg;
	}

	public String[] getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(String[] pregnancy) {
		this.pregnancy = pregnancy;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getAskId() {
		return askId;
	}

	public void setAskId(String askId) {
		this.askId = askId;
	}

	public String[] getRecordIds() {
		return recordIds;
	}

	public void setRecordIds(String[] recordIds) {
		this.recordIds = recordIds;
	}

}
