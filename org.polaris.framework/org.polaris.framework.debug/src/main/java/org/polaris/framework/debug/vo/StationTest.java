package org.polaris.framework.debug.vo;

import javax.annotation.Resource; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.debug.dao.StationDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class StationTest {
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private StationDao stationDao;
	Log log = LogFactory.getLog(getClass());
	StringBuffer sb=new StringBuffer();
	@RequestMapping("/tt")
	public StringBuffer tt(){
		Session session=null;
		SessionFactory sessionFactory=hibernateTemplate.getSessionFactory();
	try{
		session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Station st=new Station();
		st.setId("001");
		st.setMcc(460);
		st.setMnc(1);
		st.setLac(10);
		st.setCi(20);
		st.setLng(20.0);
		st.setLat(10.0);
		st.setInfo(".....");
		stationDao.insert(st);
		session.getTransaction().commit();
		sb.append("运行了try");
	}
	catch(Exception e){
		log.error("数据库操作异常",e);
		 session.getTransaction().rollback();
		sb.append("运行了catch");
		
	}
	finally{
		if(session!=null){
			if(session.isOpen()){
				session.close();
			}
		}
		sb.append("运行了finally");
	}
	log.info(sb);
	return sb;
	}
}
