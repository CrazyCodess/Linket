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
public class EggCharacterInfo
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String personality;
	@Column(length = 50)
	private String hobby;
	@Column(length = 50)
	private String talent;
	@Column(length = 50)
	private String favColor;
	@Column(length = 50)
	private String favSesson;
	@Column(length = 50)
	private String favMovie;
	@Column(length = 50)
	private String favCity;
	@Column(length = 50)
	private String favSong;
	@Column(length = 50)
	private String childhood;//0:i.e. your school life,1:relations with friends and families
	@Column(length = 50)
	private String futureGoal;
	@Column(length = 50)
	private String favFood;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;
	
	//捐赠原因
	@Column(length = 200)
	private String donation; 
	
	@Column(length = 50)
	private String resourceId;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}


	public String getPersonality()
	{
		return personality;
	}

	public void setPersonality(String personality)
	{
		this.personality = personality;
	}

	public String getHobby()
	{
		return hobby;
	}

	public void setHobby(String hobby)
	{
		this.hobby = hobby;
	}

	public String getTalent()
	{
		return talent;
	}

	public void setTalent(String talent)
	{
		this.talent = talent;
	}

	public String getFavColor()
	{
		return favColor;
	}

	public void setFavColor(String favColor)
	{
		this.favColor = favColor;
	}

	public String getFavSesson()
	{
		return favSesson;
	}

	public void setFavSesson(String favSesson)
	{
		this.favSesson = favSesson;
	}

	public String getFavMovie()
	{
		return favMovie;
	}

	public void setFavMovie(String favMovie)
	{
		this.favMovie = favMovie;
	}

	public String getFavCity()
	{
		return favCity;
	}

	public void setFavCity(String favCity)
	{
		this.favCity = favCity;
	}

	public String getFavSong()
	{
		return favSong;
	}

	public void setFavSong(String favSong)
	{
		this.favSong = favSong;
	}

	public String getChildhood()
	{
		return childhood;
	}

	public void setChildhood(String childhood)
	{
		this.childhood = childhood;
	}

	public String getFutureGoal()
	{
		return futureGoal;
	}

	public void setFutureGoal(String futureGoal)
	{
		this.futureGoal = futureGoal;
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

	public String getFavFood() {
		return favFood;
	}

	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getDonation() {
		return donation;
	}

	public void setDonation(String donation) {
		this.donation = donation;
	}

}
