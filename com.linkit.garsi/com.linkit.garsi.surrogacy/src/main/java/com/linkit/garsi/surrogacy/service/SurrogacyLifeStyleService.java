package com.linkit.garsi.surrogacy.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.surrogacy.dao.SurrogacyLifeStyleDao;
import com.linkit.garsi.surrogacy.vo.SurrogacyLifeStyle;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SurrogacyLifeStyleService {

	@Resource
	private SurrogacyLifeStyleDao surrogacyLifeStyleDao;
	
	public SurrogacyLifeStyle getByResourceId(String resourceId){
		return surrogacyLifeStyleDao.getByResourceId(resourceId);
	}
	
	public void insert(SurrogacyLifeStyle surrogacyLifeStyle){
		surrogacyLifeStyleDao.insert(surrogacyLifeStyle);
	}
	
	public void update(SurrogacyLifeStyle surrogacyLifeStyle){
		surrogacyLifeStyleDao.update(surrogacyLifeStyle);
	}
	
	public void deleteByResourceId(String resourceId){
		surrogacyLifeStyleDao.deleteByResourceId(resourceId);
	}
}
