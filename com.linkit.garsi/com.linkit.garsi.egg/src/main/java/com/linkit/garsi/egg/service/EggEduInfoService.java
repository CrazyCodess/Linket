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
import com.linkit.garsi.egg.dao.EggEduInfoDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggEduInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggEduInfoService
{
	@Resource
	private EggDao EggDao;

	@Resource
	private EggEduInfoDao eduInfoDao;

	/**
	 * 教育信息
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggEduInfo eduInfo) throws Exception
	{
		Egg Egg = EggDao.getEggById(eduInfo.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		eduInfo.setCreateTime(new Date());
		eduInfo.setUpdateTime(new Date());
		eduInfoDao.insert(eduInfo);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEduInfo(String eduInfoId) throws DataValidateException
	{
		eduInfoDao.deleteEduInfo(eduInfoId);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggEduInfo getEduInfoById(String eduInfoId)
	{
		return eduInfoDao.getEduInfoById(eduInfoId);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public EggEduInfo[] getAllEduInfo()
	{
		return eduInfoDao.getAllEduInfo();
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggEduInfo[] getAllEduInfoByEgg(String resourceId)
	{
		return eduInfoDao.getAllEduInfoByEgg(resourceId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEduInfo(EggEduInfo eduInfo) throws DataValidateException
	{
		Egg Egg = EggDao.getEggById(eduInfo.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		eduInfo.setUpdateTime(new Date());
		eduInfoDao.modifyEduInfo(eduInfo);
	}

}
