package com.linkit.garsi.surrogacy.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.linkit.garsi.surrogacy.dao.SurrogacyCharacteristicsDao;
import com.linkit.garsi.surrogacy.vo.SurrogacyCharacteristics;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SurrogacyCharacteristicsService
{

	@Resource
	private SurrogacyCharacteristicsDao surrogacyCharacteristicsDao;

	public SurrogacyCharacteristics getByResourceId(String resourceId)
	{
		return surrogacyCharacteristicsDao.getByResourceId(resourceId);
	}

	public void insert(SurrogacyCharacteristics surrogacyCharacteristics)
	{
		surrogacyCharacteristicsDao.insert(surrogacyCharacteristics);
	}

	public void update(SurrogacyCharacteristics surrogacyCharacteristics)
	{
		surrogacyCharacteristicsDao.update(surrogacyCharacteristics);
	}

	public void deleteByResourceId(String resourceId)
	{
		surrogacyCharacteristicsDao.deleteByResourceId(resourceId);
	}

}
