package com.linkit.garsi.surrogacy.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.surrogacy.dao.SurrogacyMedicalDao;
import com.linkit.garsi.surrogacy.vo.SurrogacyMedicalInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SurrogacyMedicalService {

	@Resource
	private SurrogacyMedicalDao surrogacyMedicalDao;
	
	public SurrogacyMedicalInfo getByResourceId(String resourceId){
		return surrogacyMedicalDao.getByResourceId(resourceId);
	}
	
	public void insert(SurrogacyMedicalInfo surrogacyMedicalInfo){
		surrogacyMedicalDao.insert(surrogacyMedicalInfo);
	}
	
	public void update(SurrogacyMedicalInfo surrogacyMedicalInfo){
		surrogacyMedicalDao.update(surrogacyMedicalInfo);
	}
	
	public void deleteByResourceId(String resourceId){
		surrogacyMedicalDao.deleteByResourceId(resourceId);
	}
}
