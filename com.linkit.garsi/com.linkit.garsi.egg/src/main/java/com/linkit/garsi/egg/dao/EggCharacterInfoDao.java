package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggCharacterInfo;

/**
 * 行为爱好
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggCharacterInfoDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggCharacterInfo characterInfo)
	{
		this.hibernateTemplate.save(characterInfo);

	}

	/**
	 * 删除
	 * 
	 * @param EggId
	 * @throws DataValidateException
	 */
	public void deleteEggCharacterInfo(String characterInfoId) throws DataValidateException
	{
		deleteEggCharacterInfoByAttr(characterInfoId, null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggCharacterInfoByAttr(String characterInfoId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(characterInfoId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggCharacterInfo t where 1=1");
		if (StringUtil.isNotBlank(characterInfoId))
		{
			params.add(characterInfoId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(resourceId))
		{
			params.add(resourceId);
			hql.append(" and t.resourceId=?");
		}

		this.hibernateTemplate.executeUpdate(hql.toString(), params.toArray());
	}

	/**
	 * 根据ID获取记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggCharacterInfo getEggCharacterInfoById(String characterInfoId)
	{
		return this.hibernateTemplate.queryForObject("from EggCharacterInfo t where t.id =?", new Object[] { characterInfoId }, EggCharacterInfo.class);
	}

	/**
	 * 根据EggId获取所有的记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggCharacterInfo[] getEggCharacterInfoByResourceId(String resourceId)
	{
		return this.hibernateTemplate.queryForArray("from EggCharacterInfo t where t.resourceId =? order by t.updateTime desc", new Object[] { resourceId },
				EggCharacterInfo.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggCharacterInfo(EggCharacterInfo characterInfo)
	{
		this.hibernateTemplate.update(characterInfo);
	}

}
