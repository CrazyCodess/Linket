package com.linkit.garsi.surrogacy.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.surrogacy.vo.SurrogacyMedicalInfo;

@Repository
public class SurrogacyMedicalDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(SurrogacyMedicalInfo surrogacyMedicalInfo){
		hibernateTemplate.save(surrogacyMedicalInfo);
	}
	
	public SurrogacyMedicalInfo getByResourceId(String resourceId){
		return hibernateTemplate.queryForObject("from SurrogacyMedicalInfo s where s.resourceId = ?",
				new Object[]{resourceId}, SurrogacyMedicalInfo.class);
	}
	
	public void update(SurrogacyMedicalInfo surrogacyMedicalInfo){
		hibernateTemplate.update(surrogacyMedicalInfo);
	}
	
	public void deleteByResourceId(String resourceId){
		hibernateTemplate.executeUpdate("delete from SurrogacyMedicalInfo s where s.resourceId = ?",
				new Object[]{resourceId});
	}
}
