package com.linkit.garsi.egg.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class EggDonatedRecord
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;

	@Column(length = 50)
	private String donateDate;
	@Column(length = 50)
	private String place;
	@Column(length = 50)
	private String retrievedEgg;
	
	@Column(length = 50)
	private String matureEgg;
	
	@Column(length = 50)
	private String fertilizeEgg;
	
	/**
	 * yes/no
	 */
	@Column(length = 50)
	private String pregnancy;
	
	@Column(length = 50)
	private String resourceId;
	
	@Column(length = 50)
	private String askId;
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}


	public String getDonateDate() {
		return donateDate;
	}

	public void setDonateDate(String donateDate) {
		this.donateDate = donateDate;
	}

	public String getRetrievedEgg() {
		return retrievedEgg;
	}

	public void setRetrievedEgg(String retrievedEgg) {
		this.retrievedEgg = retrievedEgg;
	}

	public String getMatureEgg() {
		return matureEgg;
	}

	public void setMatureEgg(String matureEgg) {
		this.matureEgg = matureEgg;
	}

	public String getFertilizeEgg() {
		return fertilizeEgg;
	}

	public void setFertilizeEgg(String fertilizeEgg) {
		this.fertilizeEgg = fertilizeEgg;
	}

	public String getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAskId() {
		return askId;
	}

	public void setAskId(String askId) {
		this.askId = askId;
	}

}
