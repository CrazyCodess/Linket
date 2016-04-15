package com.linkit.garsi.egg.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.dao.EggDao;
import com.linkit.garsi.egg.dao.EggDonatedAskDao;
import com.linkit.garsi.egg.dao.EggDonatedRecordDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggAsForm;
import com.linkit.garsi.egg.vo.EggAsk;
import com.linkit.garsi.egg.vo.EggDonatedRecord;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggDonatedAskService {
	@Resource
	private EggDao EggDao;

	@Resource
	private EggDonatedAskDao askDao;
	@Resource
	private EggDonatedRecordDao donateRecordDao;

	/**
	 * 捐赠历史
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggAsForm eggAsForm) throws Exception {
		Egg Egg = EggDao.getEggById(eggAsForm.getResourceId());
		if (Egg == null) {
			throw new DataValidateException("卵子信息不存在",
					ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		// 构建ask对象和捐赠对象
		EggAsk eggAsk = new EggAsk();
		eggAsk.setContactType(eggAsForm.getContactType());
		// 是否捐赠过
		eggAsk.setIsDonate(eggAsForm.getIsDonate());
		eggAsk.setCreateTime(new Date());
		eggAsk.setUpdateTime(new Date());
		eggAsk.setResourceId(eggAsForm.getResourceId());
		//插入
		askDao.insert(eggAsk);
		
		eggAsForm.setAskId(eggAsk.getId());
		
		List<EggDonatedRecord> recordList = new ArrayList<EggDonatedRecord>();

		String[] donateDates = eggAsForm.getDonateDate();

		String[] places = eggAsForm.getPlace();

		String[] retrievedEggs = eggAsForm.getRetrievedEgg();

		String[] matureEggs = eggAsForm.getMatureEgg();

		String[] fertilizeEggs = eggAsForm.getFertilizeEgg();
		String[] pregnancys = eggAsForm.getPregnancy();

		if (!(ArrayUtils.isEmpty(donateDates) || ArrayUtils.isEmpty(places)
				|| ArrayUtils.isEmpty(retrievedEggs)
				|| ArrayUtils.isEmpty(matureEggs)
				|| ArrayUtils.isEmpty(fertilizeEggs) || ArrayUtils
					.isEmpty(pregnancys))) {
			for (int i = 0; i < donateDates.length; i++) {
				EggDonatedRecord record = new EggDonatedRecord();
				record.setDonateDate(donateDates[i]);
				record.setFertilizeEgg(fertilizeEggs[i]);
				record.setMatureEgg(matureEggs[i]);
				record.setPlace(places[i]);
				record.setPregnancy(pregnancys[i]);
				record.setRetrievedEgg(retrievedEggs[i]);
				record.setResourceId(eggAsForm.getResourceId());
				record.setAskId(eggAsk.getId());
				recordList.add(record);
			}
		}
		for(EggDonatedRecord record:recordList){
			donateRecordDao.insert(record);
		}

	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggDonatedAskAndRecord(String askId)
			throws DataValidateException {
		askDao.deleteEggDonatedAsk(askId);
		donateRecordDao.deleteEggDonatedRecordByAttr(null,askId,null);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggAsk getEggDonatedAskById(String askId) {
		EggAsk ask = askDao.getEggAskById(askId);
		if(ask != null){
			//获取对应的捐赠记录
			EggDonatedRecord[] array = donateRecordDao
					.getAllEggDonatedRecordByAttr(null,ask.getId(),null);
			if(ArrayUtils.isNotEmpty(array)){
				ask.setDonateRecordList(Arrays.asList(array));
			}
		}
		return ask;
	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggAsk[] getAllEggDonatedRecordByResourceId(
			String resourceId) {
		EggAsk[] asks = askDao.getAllEggAskByAttr(null, resourceId);
		if(ArrayUtils.isNotEmpty(asks)){
			for(EggAsk ask: asks){
				//获取对应的捐赠记录
				EggDonatedRecord[] array = donateRecordDao
						.getAllEggDonatedRecordByAttr(null,ask.getId(),null);
				if(ArrayUtils.isNotEmpty(array)){
					ask.setDonateRecordList(Arrays.asList(array));
				}else{
					ask.setDonateRecordList(new ArrayList<EggDonatedRecord>());
				}
			}
		}
		return asks;
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggDonatedAskRecord(EggAsForm eggAsForm)
			throws DataValidateException {
		Egg Egg = EggDao.getEggById(eggAsForm.getResourceId());
		if (Egg == null) {
			throw new DataValidateException("卵子信息不存在",
					ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		// 构建ask对象和捐赠对象
		EggAsk eggAsk = new EggAsk();
		eggAsk.setId(eggAsForm.getAskId());
		eggAsk.setContactType(eggAsForm.getContactType());
		// 是否捐赠过
		eggAsk.setIsDonate(eggAsForm.getIsDonate());
		eggAsk.setCreateTime(new Date());
		eggAsk.setUpdateTime(new Date());
		eggAsk.setResourceId(eggAsForm.getResourceId());
		List<EggDonatedRecord> recordList = new ArrayList<EggDonatedRecord>();

		String[] donateDates = eggAsForm.getDonateDate();

		String[] places = eggAsForm.getPlace();

		String[] retrievedEggs = eggAsForm.getRetrievedEgg();

		String[] matureEggs = eggAsForm.getMatureEgg();

		String[] fertilizeEggs = eggAsForm.getFertilizeEgg();
		String[] pregnancys = eggAsForm.getPregnancy();
		
		String[] recordIds = eggAsForm.getRecordIds();

		if (!(ArrayUtils.isEmpty(donateDates) || ArrayUtils.isEmpty(places)
				|| ArrayUtils.isEmpty(retrievedEggs)
				|| ArrayUtils.isEmpty(matureEggs)
				|| ArrayUtils.isEmpty(fertilizeEggs) || ArrayUtils
					.isEmpty(pregnancys))|| ArrayUtils.isEmpty(recordIds)) {
			for (int i = 0; i < donateDates.length; i++) {
				EggDonatedRecord record = new EggDonatedRecord();
				record.setDonateDate(donateDates[i]);
				record.setFertilizeEgg(fertilizeEggs[i]);
				record.setMatureEgg(matureEggs[i]);
				record.setPlace(places[i]);
				record.setPregnancy(pregnancys[i]);
				record.setRetrievedEgg(retrievedEggs[i]);
				record.setResourceId(eggAsForm.getResourceId());
				record.setAskId(eggAsk.getId());
				record.setId(recordIds[i]);
				recordList.add(record);
			}
		}

		for(EggDonatedRecord record:recordList){
			donateRecordDao.modifyEggDonatedRecord(record);
		}
		askDao.modifyEggDonatedAsk(eggAsk);
	}

}
