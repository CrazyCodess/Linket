package org.polaris.framework.debug.vo;

import javax.persistence.Column;     

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 基站实体类
 * 
 * @author wang.sheng
 * http://blog.csdn.net/lyq123333321/article/details/44217409
 * http://blog.csdn.net/lisa890608/article/details/9624669
 * http://sishuok.com/forum/blogPost/list/2461.html
 */
@Table//缺省name，系统默认采用类名作为映射表的表名
@Entity
public class Station
{
	@Id//主键
	@GenericGenerator(name = "system-uuid", strategy = "uuid") //自定义主键生成策略   
	//uuid: 采用128位的uuid算法生成主键，uuid被编码为一个32位16进制数字的字符串。占用空间大（字符串类型）。 
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32)
	private String id;
	@Column
	@Range(min = 460, max = 460, message = "国家号必须为460")
	private int mcc;
	@Column
	@Range(min = 0, max = 2, message = "超出范围: 0-移动,1-联通,2-电信")
	private int mnc;
	@Column
	private int lac;
	@Column
	private int ci;
	@Column
	private double lng;
	@Column
	private double lat;
	@Column(length = 400)
	@Length(max = 200, message = "超出最大长度200个字符")
	private String info;

	@Override
	public String toString()
	{
		return new StringBuffer().append("LNG:").append(lng).append(",LAT:").append(lat).append(",INFO:").append(info).toString();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getMcc()
	{
		return mcc;
	}

	public void setMcc(int mcc)
	{
		this.mcc = mcc;
	}

	public int getMnc()
	{
		return mnc;
	}

	public void setMnc(int mnc)
	{
		this.mnc = mnc;
	}

	public int getLac()
	{
		return lac;
	}

	public void setLac(int lac)
	{
		this.lac = lac;
	}

	public int getCi()
	{
		return ci;
	}

	public void setCi(int ci)
	{
		this.ci = ci;
	}

	public double getLng()
	{
		return lng;
	}

	public void setLng(double lng)
	{
		this.lng = lng;
	}

	public double getLat()
	{
		return lat;
	}

	public void setLat(double lat)
	{
		this.lat = lat;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

}
