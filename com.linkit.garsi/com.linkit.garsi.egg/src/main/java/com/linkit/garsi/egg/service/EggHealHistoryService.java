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
import com.linkit.garsi.egg.dao.EggHealHistoryDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggHealHistory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggHealHistoryService
{
	@Resource
	private EggDao EggDao;

	@Resource
	private EggHealHistoryDao healHistoryDao;

	/**
	 * 生育历史
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggHealHistory healHistory) throws Exception
	{
		Egg Egg = EggDao.getEggById(healHistory.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		healHistory.setCreateTime(new Date());
		healHistory.setUpdateTime(new Date());
		healHistoryDao.insert(healHistory);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggHealHistory(String healHistoryId) throws DataValidateException
	{
		healHistoryDao.deleteEggHealHistory(healHistoryId);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggHealHistory getEggHealHistoryById(String healHistoryId)
	{
		return healHistoryDao.getEggHealHistoryById(healHistoryId);
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggHealHistory[] getAllEggHealHistoryByResourceId(String resourceId)
	{
		return healHistoryDao.getAllEggHealHistoryByResourceId(resourceId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggHealHistory(EggHealHistory healHistory) throws DataValidateException
	{
		Egg Egg = EggDao.getEggById(healHistory.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		healHistory.setUpdateTime(new Date());
		healHistoryDao.modifyEggHealHistory(healHistory);
	}

}
