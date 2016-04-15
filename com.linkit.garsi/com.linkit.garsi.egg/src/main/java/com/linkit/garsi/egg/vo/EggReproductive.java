package com.linkit.garsi.egg.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 生育历史
 * 
 * @author Administrator
 * 
 */
@Entity
@Table
public class EggReproductive
{
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column(length = 50)
	private String resourceId;
	/**
	 * 怀孕
	 */
	@Column(length = 50)
	private String pregnancy;
	@Column(length = 50)
	private String child;
	@Column(length = 50)
	private String regularPeriod;
	@Column(length = 50)
	private String ageFmp;
	/**
	 * 姨妈周期
	 */
	@Column(length = 50)
	private String dayBleeding;
	
	/**
	 * form of birth control are you using 节育周期
	 */
	@Column(length = 50)
	private String hatForm;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}


	public String getPregnancy()
	{
		return pregnancy;
	}

	public void setPregnancy(String pregnancy)
	{
		this.pregnancy = pregnancy;
	}

	public String getChild()
	{
		return child;
	}

	public void setChild(String child)
	{
		this.child = child;
	}

	public String getRegularPeriod()
	{
		return regularPeriod;
	}

	public void setRegularPeriod(String regularPeriod)
	{
		this.regularPeriod = regularPeriod;
	}

	public String getAgeFmp()
	{
		return ageFmp;
	}

	public void setAgeFmp(String ageFmp)
	{
		this.ageFmp = ageFmp;
	}

	public String getDayBleeding()
	{
		return dayBleeding;
	}

	public void setDayBleeding(String dayBleeding)
	{
		this.dayBleeding = dayBleeding;
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

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getHatForm() {
		return hatForm;
	}

	public void setHatForm(String hatForm) {
		this.hatForm = hatForm;
	}

}
