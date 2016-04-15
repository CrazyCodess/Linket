package com.linkit.garsi.surrogacy.service;

import java.io.InputStream;

import javax.annotation.Resource;

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
import com.linkit.garsi.common.resource.dao.AttachmentDao;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.Attachment;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.surrogacy.dao.SurrogacyInfoDao;
import com.linkit.garsi.surrogacy.vo.SurrogacyInfo;
import com.linkit.garsi.surrogacy.vo.SurrogacySearchForm;

/**
 * 代母服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SurrogacyInfoService
{
	@Resource
	private SurrogacyInfoDao surrogacyInfoDao;
	@Resource
	private ResourceService resourceService;
	@Resource
	private SurrogacyLifeStyleService surrogacyLifeStyleService;
	@Resource
	private SurrogacyMedicalService surrogacyMedicalService;
	@Resource
	private SurrogacyCharacteristicsService surrogacyCharacteristicsService;
	@Resource
	private AttachmentDao attachmentDao;

	@Resource
	private BlobContentService blobContentService;

	Log log = LogFactory.getLog(getClass());

	public SurrogacyInfo getSurrogacyByResourceId(String resourceId)
	{
		return surrogacyInfoDao.getSurrogacyByResourceId(resourceId);
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

	/**
	 * 删除指定代母.同时删除资源配置表记录,代码关联信息
	 * 
	 * @param id
	 */
	public void deleteByResourceId(String resourceId)
	{
		this.surrogacyInfoDao.deleteByResourceId(resourceId);
		surrogacyLifeStyleService.deleteByResourceId(resourceId);
		surrogacyMedicalService.deleteByResourceId(resourceId);
		surrogacyCharacteristicsService.deleteByResourceId(resourceId);
		attachmentDao.deleteByDetailId(resourceId);
		resourceService.deleteByDetailId(resourceId);
	}

	/**
	 * 加入一个代母资源
	 * 
	 * @param surrogacy
	 * @param userinfo
	 */
	public void insert(SurrogacyInfo surrogacy, Userinfo userinfo)
	{
		surrogacyInfoDao.insert(surrogacy);
		// 同步向资源表中添加资源配置信息
		GResource resource = new GResource();
		resource.setResourceType(surrogacy.getResourceType());
		resource.setDetailId(surrogacy.getId());
		resource.setCreateTime(System.currentTimeMillis());
		resource.setCreatorId(userinfo.getUserId());
		resource.setCustomerId(null);
		resource.setProcessState(ProcessState.FREE);
		resource.setResourceState(ResourceState.ENABLE);
		resource.setTitle(surrogacy.getTitle());
		resourceService.insert(resource);
		log.info("insert Surrogacy successful! title: " + resource.getTitle());
	}

	/**
	 * 修改代母资源
	 * 
	 * @param surrogacy
	 */
	public void update(SurrogacyInfo surrogacy)
	{
		surrogacyInfoDao.update(surrogacy);
	}

	
	/**
	 * 查询所有代码信息
	 * @param userId
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Object> getPagingSurrogacyByQueryForm(SurrogacySearchForm searchForm, int start, int limit)
	{
		return surrogacyInfoDao.getPagingSurrogacysByQueryForm(searchForm, start, limit);
	}
	
	/**
	 * 查询用户创建的代母信息
	 * 
	 * @param userId
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Object> getPagingSurrogacyByCreatorQueryForm(String userId, SurrogacySearchForm searchForm, int start, int limit)
	{
		return surrogacyInfoDao.getPagingSurrogacysByCreatorQueryForm(userId, searchForm, start, limit);
	}
	
	/**
	 * 查询所有用户可选的代母信息
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Object> getPagingSurrogacyByQueryFormEnable(SurrogacySearchForm searchForm, int start, int limit)
	{
		return surrogacyInfoDao.getPagingSurrogacysByQueryFormEnable(searchForm, start, limit);
	}

	/**
	 * 查询用户选定的代母
	 * @param userId
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Object> getPagingSurrogacyByCustomerIdAndName(
			String userId, String name, int start, int limit) {
		return surrogacyInfoDao.getPagingSurrogacyByCustomerIdAndName(userId, name, start, limit);
	}
}
