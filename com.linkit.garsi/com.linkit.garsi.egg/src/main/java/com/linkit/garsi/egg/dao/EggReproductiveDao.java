package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggReproductive;

/**
 * 生育历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggReproductiveDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggReproductive reproductive)
	{
		this.hibernateTemplate.save(reproductive);

	}

	/**
	 * 删除
	 * 
	 * @param EggId
	 * @throws DataValidateException
	 */
	public void deleteEggReproductive(String reproductiveId) throws DataValidateException
	{
		deleteEggReproductiveByAttr(reproductiveId, null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggReproductiveByAttr(String reproductiveId, String eggId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(eggId) && StringUtil.isBlank(reproductiveId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggReproductive t where 1=1");
		if (StringUtil.isNotBlank(reproductiveId))
		{
			params.add(reproductiveId);
			hql.append(" and t.id=?");
		}
		if (StringUtil.isNotBlank(eggId))
		{
			params.add(eggId);
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
	public EggReproductive getEggReproductiveById(String reproductiveId)
	{
		return this.hibernateTemplate.queryForObject("from EggReproductive t where t.id =?", new Object[] { reproductiveId }, EggReproductive.class);
	}

	/**
	 * 根据eggId获取所有的记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggReproductive[] getEggReproductiveByResourceId(String resourceId)
	{
		return this.hibernateTemplate.queryForArray("from EggReproductive t where t.resourceId =? order by t.updateTime desc", new Object[] { resourceId },
				EggReproductive.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggReproductive(EggReproductive reproductive)
	{
		this.hibernateTemplate.update(reproductive);
	}

}
