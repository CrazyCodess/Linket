package com.linkit.garsi.common.document.service;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.common.lob.service.BlobContentService;
import org.polaris.framework.common.lob.vo.BlobContent;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.document.dao.DocumentDao;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.document.vo.QueryForm;
import com.linkit.garsi.common.resource.dao.ResourceDao;
import com.linkit.garsi.common.utils.RoleUtils;

/**
 * 资料服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DocumentService
{
	@Resource
	private DocumentDao documentDao;
	@Resource
	private BlobContentService blobContentService;
	@Resource
	private AuthorizeService authorizeService;
	@Resource
	private ResourceDao resourceDao;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 上传一个资料
	 * 
	 * @param documentForm
	 * @param userinfo
	 */
	public void upload(String userId, String title, String type, InputStream is, String fileName, long size)
	{
		log.info("upload document: " + fileName + ", size: " + FileUtils.byteCountToDisplaySize(size));
		BlobContent blobContent = blobContentService.upload(is, fileName, size);
		Document document = new Document();
		document.setContentId(blobContent.getId());
		document.setUserId(userId);
		document.setTime(System.currentTimeMillis());
		document.setTitle(title);
		document.setType(type);
		documentDao.insert(document);
	}

	public Document getDocument(String id)
	{
		return documentDao.getDocument(id);
	}

	public Document[] getDocumentsByUserId(String userId)
	{
		return documentDao.getDocumentsByUserId(userId);
	}

	/**
	 * 获取用户所属的所有资料
	 * 
	 * @param userId
	 * @param queryForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Document> getDocumentsByUserId(String userId, QueryForm queryForm, int start, int limit)
	{
		Role[] roles = authorizeService.getRolesByUser(userId);
		if (RoleUtils.containsCompanyRole(roles))
		{
			// 公司用户
			return documentDao.getDocumentsByUserId(userId, queryForm, start, limit);
		}
		else if (RoleUtils.containsCustomerRole(roles))
		{
			// 顾客用户
			return documentDao.getFinallyDocumentsByCustomer(userId, queryForm, start, limit);
		}
		else
		{
			return new PagingResult<Document>(0, new Document[0]);
		}
	}

	/**
	 * 删除指定资料
	 * 
	 * @param id
	 */
	public void delete(String id)
	{
		Document document = documentDao.getDocument(id);
		if (document != null)
		{
			blobContentService.delete(document.getContentId());
			documentDao.delete(id);
		}
	}

}
