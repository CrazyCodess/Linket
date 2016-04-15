package com.linkit.garsi.egg.service;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.lob.service.BlobContentService;
import org.polaris.framework.common.lob.vo.BlobContent;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.constant.ErrorCodeConstant;
import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.Attachment;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.egg.dao.EggCharacterInfoDao;
import com.linkit.garsi.egg.dao.EggDao;
import com.linkit.garsi.egg.dao.EggEduInfoDao;
import com.linkit.garsi.egg.dao.EggFamilyHistoryDao;
import com.linkit.garsi.egg.dao.EggHealHistoryDao;
import com.linkit.garsi.egg.dao.EggReproductiveDao;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggForm;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EggService
{
	@Resource
	private EggDao eggDao;
	@Resource
	private EggCharacterInfoDao characterInfoDao;
	@Resource
	private EggEduInfoDao eduInfoDao;
	@Resource
	private EggFamilyHistoryDao familyHistoryDao;
	@Resource
	private EggHealHistoryDao healHistoryDao;
	@Resource
	private EggReproductiveDao reproductiveDao;
	@Resource
	private ResourceService resourceService;
	
	@Resource
	private BlobContentService blobContentService;
	
	Log log = LogFactory.getLog(getClass());

	/**
	 * 添加代孕妈妈
	 * 
	 * @param user
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void insert(Egg egg, Userinfo userinfo) throws Exception
	{
		egg.setCreateTime(new Date());
		egg.setUpdateTime(new Date());
		eggDao.insert(egg);
		// 同步向资源表中添加资源配置信息
		GResource resource = new GResource();
		resource.setResourceType(egg.getResourceType());
		resource.setDetailId(egg.getId());
		resource.setCreateTime(System.currentTimeMillis());
		resource.setCreatorId(userinfo.getUserId());
		resource.setCustomerId(null);
		resource.setProcessState(ProcessState.FREE);
		resource.setResourceState(ResourceState.ENABLE);
		resource.setTitle(egg.getTitle());
		resourceService.insert(resource);
		log.info("insert Surrogacy successful! title: " + resource.getTitle());
		
	}

	/**
	 * 删除
	 * 
	 * @param eggId
	 * @throws DataValidateException
	 */
	public void deleteEgg(String eggId) throws DataValidateException
	{
		characterInfoDao.deleteEggCharacterInfoByAttr(null, eggId);
		eduInfoDao.deleteEduInfoByAttr(null, eggId);
		familyHistoryDao.deleteEggFamilyHistoryByAttr(null, eggId);
		healHistoryDao.deleteEggHealHistoryByAttr(null, eggId);
		reproductiveDao.deleteEggReproductiveByAttr(null, eggId);
		eggDao.deleteEgg(eggId);
	}

	/**
	 * 获取分页查询
	 * 
	 * @param resourceType
	 * @param creatorId
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Object> getPagingEggByAttr(EggForm eggForm, int start, int limit)
	{
		return eggDao.getPagingEggByAttr(eggForm, start, limit);
	}

	/**
	 * 根据ID获取一条记录
	 * 
	 * @param userId
	 * @return
	 */
	public Egg getEggById(String eggId)
	{
		return eggDao.getEggById(eggId);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	public Egg[] getAllEgg()
	{
		return eggDao.getAllEgg();
	}

	/**
	 * 修改
	 * 
	 */
	public void modifyEgg(Egg egg)throws Exception
	{
		Egg eggOldInfo = eggDao.getEggById(egg.getId());
		if(eggOldInfo == null){
			throw new DataValidateException("卵子信息不存在", ErrorCodeConstant.GOODS_STOCK_NOT_ENOUGH);
		}
		//把egg的值更新到eggOldInfo中
		BeanUtils.copyProperties(eggOldInfo,egg);
		eggOldInfo.setUpdateTime(new Date());
		eggDao.modifyEgg(eggOldInfo);
	}
	
	
	public PagingResult<Object> getAllCustomerSelect(String eggName,String surrName,int start, int limit){
		return eggDao.getAllCustomerSelect(eggName, surrName, start, limit);
	}
	
	/**
	 * 上传图片附件
	 * 
	 * @param resourceId
	 * @param is
	 * @param fileName
	 * @param length
	 */
	public void uploadAttachment(String resourceId, InputStream is, String fileName, long length)
	{
		BlobContent blobContent = blobContentService.upload(is, fileName, length);
		Attachment attachment = new Attachment();
		attachment.setResourceId(resourceId);
		attachment.setContentId(blobContent.getId());
		resourceService.insert(attachment);
	}


}
