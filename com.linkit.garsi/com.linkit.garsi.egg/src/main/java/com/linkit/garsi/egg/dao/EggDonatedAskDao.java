package com.linkit.garsi.egg.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.vo.EggAsk;

/**
 * 捐赠历史
 * 
 * @author Administrator
 * 
 */
@Repository
public class EggDonatedAskDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(EggAsk eggAsk)
	{
		this.hibernateTemplate.save(eggAsk);

	}

	/**
	 * 删除
	 * 
	 * @param familyMemberId
	 * @throws DataValidateException
	 */
	public void deleteEggDonatedAsk(String askId) throws DataValidateException
	{
		deleteEggDonatedAskByAttr(askId,null);
	}

	/**
	 * 删除
	 * 
	 * @param EduInfoId
	 * @throws DataValidateException
	 */
	public void deleteEggDonatedAskByAttr(String askId, String resourceId) throws DataValidateException
	{
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isBlank(resourceId) && StringUtil.isBlank(askId))
		{
			throw new DataValidateException("参数为空", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		StringBuffer hql = new StringBuffer("delete from EggAsk t where 1=1");
		if (StringUtil.isNotBlank(askId))
		{
			params.add(askId);
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
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggAsk getEggAskById(String id)
	{
		EggAsk[]  arrar = getAllEggAskByAttr(id,null);
		if(ArrayUtils.isNotEmpty(arrar)){
			return arrar[0];
		}else{
			return null;
		}
	}
	
	
	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggAsk[] getAllEggAskByAttr(String id, String resourceId)
	{

		StringBuffer hql = new StringBuffer("from EggAsk t where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(id))
		{
			hql.append(" and t.id = ?");
			params.add(id);
		}

		if (StringUtils.isNotEmpty(resourceId))
		{
			hql.append(" and t.resourceId = ?");
			params.add(resourceId);
		}
		
		hql.append(" order by t.updateTime desc");
		return this.hibernateTemplate.queryForArray(hql.toString(), params.toArray(), EggAsk.class);
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEggDonatedAsk(EggAsk ask)
	{
		this.hibernateTemplate.update(ask);
	}

}
