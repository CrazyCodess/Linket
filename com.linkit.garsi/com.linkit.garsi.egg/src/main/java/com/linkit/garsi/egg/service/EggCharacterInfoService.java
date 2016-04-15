package com.linkit.garsi.egg.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.dao.EggCharacterInfoDao;
import com.linkit.garsi.egg.dao.EggDao;
import com.linkit.garsi.egg.vo.EggCharacterInfo;
import com.linkit.garsi.egg.vo.Egg;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggCharacterInfoService
{
	@Resource
	private EggDao EggDao;
	@Resource
	private EggCharacterInfoDao characterInfoDao;

	/**
	 * 行为爱好
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggCharacterInfo characterInfo) throws Exception
	{
		Egg Egg = EggDao.getEggById(characterInfo.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		characterInfo.setCreateTime(new Date());
		characterInfo.setUpdateTime(new Date());
		characterInfoDao.insert(characterInfo);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggCharacterInfo(String characterInfoId) throws DataValidateException
	{
		characterInfoDao.deleteEggCharacterInfo(characterInfoId);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggCharacterInfo getEggCharacterInfoById(String characterInfoId)
	{
		return characterInfoDao.getEggCharacterInfoById(characterInfoId);
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggCharacterInfo[] getAllEggCharacterInfoByResourceId(String resourceId)
	{
		return characterInfoDao.getEggCharacterInfoByResourceId(resourceId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggCharacterInfo(EggCharacterInfo characterInfo) throws DataValidateException
	{
		Egg Egg = EggDao.getEggById(characterInfo.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		characterInfo.setUpdateTime(new Date());
		characterInfoDao.modifyEggCharacterInfo(characterInfo);
	}

}
