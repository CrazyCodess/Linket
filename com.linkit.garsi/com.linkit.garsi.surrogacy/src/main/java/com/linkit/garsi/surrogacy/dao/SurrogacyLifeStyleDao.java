package com.linkit.garsi.surrogacy.dao;

import javax.annotation.Resource;

import org.polaris.framework.common.dao.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.surrogacy.vo.SurrogacyLifeStyle;

@Repository
public class SurrogacyLifeStyleDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(SurrogacyLifeStyle surrogacyLifeStyle){
		hibernateTemplate.save(surrogacyLifeStyle);
	}
	
	public SurrogacyLifeStyle getByResourceId(String resourceId){
		return hibernateTemplate.queryForObject("from SurrogacyLifeStyle s where s.resourceId = ?",
				new Object[]{resourceId}, SurrogacyLifeStyle.class);
	}
	
	public void update(SurrogacyLifeStyle surrogacyLifeStyle){
		hibernateTemplate.update(surrogacyLifeStyle);
	}
	
	public void deleteByResourceId(String resourceId){
		hibernateTemplate.executeUpdate("delete from SurrogacyLifeStyle s where s.resourceId = ?",
				new Object[]{resourceId});
	}
}
