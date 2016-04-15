package com.linkit.garsi.surrogacy.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.surrogacy.vo.SurrogacyCharacteristics;

@Repository
public class SurrogacyCharacteristicsDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void insert(SurrogacyCharacteristics surrogacyCharacteristics){
		hibernateTemplate.save(surrogacyCharacteristics);
	}
	
	public SurrogacyCharacteristics getByResourceId(String resourceId){
		return hibernateTemplate.queryForObject("from SurrogacyCharacteristics s where s.resourceId = ?",
				new Object[]{resourceId},SurrogacyCharacteristics.class);
	}
	
	public void update(SurrogacyCharacteristics surrogacyCharacteristics){
		hibernateTemplate.update(surrogacyCharacteristics);
	}
	
	public void deleteByResourceId(String resourceId){
		hibernateTemplate.executeUpdate("delete from SurrogacyCharacteristics t where t.resourceId = ?", new Object[]{ resourceId });
	}
}
