package com.linkit.garsi.egg.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.dao.EggDao;
import com.linkit.garsi.egg.dao.EggFamilyHistoryDao;
import com.linkit.garsi.egg.dao.EggFamilyMemberDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggFamilyForm;
import com.linkit.garsi.egg.vo.EggFamilyHistory;
import com.linkit.garsi.egg.vo.EggFamilyMember;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggFamilyHistoryService {
	@Resource
	private EggDao EggDao;

	@Resource
	private EggFamilyHistoryDao familyHistoryDao;

	@Resource
	private EggFamilyMemberDao memberDao;

	/**
	 * 家庭历史
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(EggFamilyForm eggFamilyForm) throws Exception {
		EggFamilyHistory familyHistory = new EggFamilyHistory();

		Egg Egg = EggDao.getEggById(eggFamilyForm.getResourceId());
		if (Egg == null) {
			throw new DataValidateException("卵子信息不存在",
					ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}

		familyHistory.setFamGenHistory(eggFamilyForm.getFamGenHistory());
		familyHistory.setFamHistory(eggFamilyForm.getFamHistory());
		familyHistory.setFamPsyIssues(eggFamilyForm.getFamPsyIssues());
		familyHistory.setResourceId(eggFamilyForm.getResourceId());
		familyHistory.setCreateTime(new Date());
		familyHistory.setUpdateTime(new Date());
		familyHistoryDao.insert(familyHistory);
		eggFamilyForm.setFamilyHistoryId(familyHistory.getId());

//		String[] memberIds = eggFamilyForm.getMemberIds();

		String[] memberRelations = eggFamilyForm.getMemberRelation();
		Integer[] ages = eggFamilyForm.getAge();
		String[] ethnicOrigins = eggFamilyForm.getEthnicOrigin();
		Double[] heights = eggFamilyForm.getHeight();
		Double[] weights = eggFamilyForm.getWeight();
		String[] eyeColors = eggFamilyForm.getEyeColor();
		String[] hairColors = eggFamilyForm.getHairColor();
		String[] healthStatus = eggFamilyForm.getHealthStatus();
		Integer[] deceaseAge = eggFamilyForm.getDeceaseAge();
		String[] deceaseReson = eggFamilyForm.getDeceaseReson();

		if (!(ArrayUtils.isEmpty(memberRelations)
				|| ArrayUtils.isEmpty(ages)
				|| ArrayUtils.isEmpty(ethnicOrigins)
				|| ArrayUtils.isEmpty(heights) || ArrayUtils.isEmpty(weights)
				|| ArrayUtils.isEmpty(eyeColors) || ArrayUtils.isEmpty(hairColors)
				|| ArrayUtils.isEmpty(healthStatus) || ArrayUtils.isEmpty(deceaseAge) || ArrayUtils
					.isEmpty(deceaseReson))) {
			for (int i=0;i<memberRelations.length;i++) {
				EggFamilyMember member =new EggFamilyMember();
				// 设置属于哪个familyHistory 插入时属于同一个
				member.setFamilyHistoryId(familyHistory.getId());
				member.setResourceId(familyHistory.getResourceId());
				member.setMemberRelation(memberRelations[i]);
				member.setAge(ages[i]);
				member.setEthnicOrigin(ethnicOrigins[i]);
				member.setHeight(heights[i]);
				member.setWeight(weights[i]);
				member.setEyeColor(eyeColors[i]);
				member.setHairColor(hairColors[i]);
				member.setHealthStatus(healthStatus[i]);
				member.setDeceaseAge(deceaseAge[i]);
				member.setDeceaseReson(deceaseReson[i]);
				member.setCreateTime(new Date());
				member.setUpdateTime(new Date());
				memberDao.insert(member);
			}
		}
	}

	/**
	 * 删除一条记录
	 * 
	 * @param
	 * @throws DataValidateException
	 */
	public void deleteEggFamilyHistory(String familyHistoryId)
			throws DataValidateException {
		familyHistoryDao.deleteEggFamilyHistory(familyHistoryId);
		memberDao.deleteEggFamilyHistoryByAttr(null, familyHistoryId, null);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public EggFamilyHistory getEggFamilyHistoryById(String familyHistoryId) {
		EggFamilyHistory[] familyHistorys = familyHistoryDao
				.getAllEggFamilyHistoryByAttr(familyHistoryId, null);
		if (familyHistorys != null && familyHistorys.length > 0) {
			return familyHistorys[0];
		} else {
			return null;
		}

	}

	/**
	 * 获取卵子下所有记录
	 * 
	 * @return
	 */
	public EggFamilyHistory[] getAllEggFamilyHistoryByEgg(
			String familyHistoryId, String resourceId) {
		return familyHistoryDao.getAllEggFamilyHistoryByAttr(familyHistoryId,
				resourceId);
	}

	/**
	 * 修改
	 * 
	 * @throws DataValidateException
	 * 
	 */
	public void modifyEggFamilyHistory(@RequestBody EggFamilyForm eggFamilyForm) throws DataValidateException {
		Egg Egg = EggDao.getEggById(eggFamilyForm.getResourceId());
		if (Egg == null) {
			throw new DataValidateException("卵子信息不存在",
					ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		
		EggFamilyHistory familyHistory = new EggFamilyHistory();
		familyHistory.setId(eggFamilyForm.getFamilyHistoryId());
		familyHistory.setFamGenHistory(eggFamilyForm.getFamGenHistory());
		familyHistory.setFamHistory(eggFamilyForm.getFamHistory());
		familyHistory.setFamPsyIssues(eggFamilyForm.getFamPsyIssues());
		familyHistory.setResourceId(eggFamilyForm.getResourceId());
		familyHistory.setUpdateTime(new Date());
		familyHistoryDao.modifyEggFamilyHistory(familyHistory);

		String[] memberIds = eggFamilyForm.getMemberIds();
		String[] memberRelations = eggFamilyForm.getMemberRelation();
		Integer[] ages = eggFamilyForm.getAge();
		String[] ethnicOrigins = eggFamilyForm.getEthnicOrigin();
		Double[] heights = eggFamilyForm.getHeight();
		Double[] weights = eggFamilyForm.getWeight();
		String[] eyeColors = eggFamilyForm.getEyeColor();
		String[] hairColors = eggFamilyForm.getHairColor();
		String[] healthStatus = eggFamilyForm.getHealthStatus();
		Integer[] deceaseAge = eggFamilyForm.getDeceaseAge();
		String[] deceaseReson = eggFamilyForm.getDeceaseReson();

		if (!(ArrayUtils.isEmpty(memberIds)
				||ArrayUtils.isEmpty(memberRelations)
				|| ArrayUtils.isEmpty(ages)
				|| ArrayUtils.isEmpty(ethnicOrigins)
				|| ArrayUtils.isEmpty(heights) || ArrayUtils.isEmpty(weights)
				|| ArrayUtils.isEmpty(eyeColors) || ArrayUtils.isEmpty(hairColors)
				|| ArrayUtils.isEmpty(healthStatus) || ArrayUtils.isEmpty(deceaseAge) || ArrayUtils
					.isEmpty(deceaseReson))) {
			for (int i=0;i<memberRelations.length;i++) {
				EggFamilyMember member =new EggFamilyMember();
				member.setId(memberIds[i]);
				// 设置属于哪个familyHistory 插入时属于同一个
				member.setFamilyHistoryId(familyHistory.getId());
				member.setResourceId(familyHistory.getResourceId());
				member.setMemberRelation(memberRelations[i]);
				member.setAge(ages[i]);
				member.setEthnicOrigin(ethnicOrigins[i]);
				member.setHeight(heights[i]);
				member.setWeight(weights[i]);
				member.setEyeColor(eyeColors[i]);
				member.setHairColor(hairColors[i]);
				member.setHealthStatus(healthStatus[i]);
				member.setDeceaseAge(deceaseAge[i]);
				member.setDeceaseReson(deceaseReson[i]);
				member.setUpdateTime(new Date());
				memberDao.modifyEggFamilyMember(member);
			}
		}
	}

}
