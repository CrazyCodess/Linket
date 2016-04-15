package com.linkit.garsi.egg.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.dao.EggDao;
import com.linkit.garsi.egg.dao.EggReproductiveDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggReproductive;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggReproductiveService
{
	@Resource
	private EggDao EggDao;

	@Resource
	private EggReproductiveDao reproductiveDao;

	/**
	 * 生育历史
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggReproductive reproductive) throws Exception
	{
		Egg Egg = EggDao.getEggById(reproductive.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		reproductive.setCreateTime(new Date());
		reproductive.setUpdateTime(new Date());
		reproductiveDao.insert(reproductive);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggReproductive(String reproductiveId) throws DataValidateException
	{
		reproductiveDao.deleteEggReproductive(reproductiveId);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggReproductive getEggReproductiveById(String reproductiveId)
	{
		return reproductiveDao.getEggReproductiveById(reproductiveId);
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggReproductive[] getAllEggReproductiveByResourceId(String resourceId)
	{
		return reproductiveDao.getEggReproductiveByResourceId(resourceId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggReproductive(EggReproductive reproductive) throws DataValidateException
	{
		Egg Egg = EggDao.getEggById(reproductive.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		reproductive.setUpdateTime(new Date());
		reproductiveDao.modifyEggReproductive(reproductive);
	}

}
