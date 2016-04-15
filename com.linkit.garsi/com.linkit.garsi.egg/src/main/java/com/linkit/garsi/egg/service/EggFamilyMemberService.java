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
import com.linkit.garsi.egg.dao.EggFamilyHistoryDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggFamilyHistory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggFamilyMemberService
{
	@Resource
	private EggDao EggDao;

	@Resource
	private EggFamilyHistoryDao familyHistoryDao;

	/**
	 * 家庭历史
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggFamilyHistory familyHistory) throws Exception
	{
		Egg Egg = EggDao.getEggById(familyHistory.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		familyHistory.setCreateTime(new Date());
		familyHistory.setUpdateTime(new Date());
		familyHistoryDao.insert(familyHistory);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyHistory(String familyHistoryId) throws DataValidateException
	{
		familyHistoryDao.deleteEggFamilyHistory(familyHistoryId);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggFamilyHistory getEggFamilyHistoryById(String familyHistoryId)
	{
		EggFamilyHistory[] familyHistorys = familyHistoryDao.getAllEggFamilyHistoryByAttr(familyHistoryId, null);
		if (familyHistorys != null && familyHistorys.length > 0)
		{
			return familyHistorys[0];
		}
		else
		{
			return null;
		}

	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggFamilyHistory[] getAllEggFamilyHistoryByEgg(String familyHistoryId, String eggId)
	{
		return familyHistoryDao.getAllEggFamilyHistoryByAttr(familyHistoryId, eggId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggFamilyHistory(EggFamilyHistory familyHistory) throws DataValidateException
	{
		Egg Egg = EggDao.getEggById(familyHistory.getResourceId());
		if (Egg == null)
		{
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		familyHistory.setUpdateTime(new Date());
		familyHistoryDao.modifyEggFamilyHistory(familyHistory);
	}

}
