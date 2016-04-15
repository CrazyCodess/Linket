package com.linkit.garsi.common.document.controller;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.document.service.DocumentService;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.document.vo.DocumentForm;
import com.linkit.garsi.common.document.vo.QueryForm;

/**
 * 资料服务控制器
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/document")
@SessionAttributes(Userinfo.KEY)
public class DocumentController
{
	@Resource
	private DocumentService documentService;

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Document getDocument(@PathVariable String id)
	{
		return documentService.getDocument(id);
	}

	/**
	 * 获取该用户的资料列表
	 * 
	 * @param userinfo
	 * @param queryForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public PagingResult<Document> list(@ModelAttribute Userinfo userinfo, QueryForm queryForm, int start, int limit)
	{
		return documentService.getDocumentsByUserId(userinfo.getUserId(), queryForm, start, limit);
	}

	/**
	 * 上传资料
	 * 
	 * @param documentForm
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public FormResult upload(DocumentForm documentForm, @ModelAttribute Userinfo userinfo)
	{
		FormResult formResult = new FormResult();
		MultipartFile file = documentForm.getFile();
		InputStream is = null;
		try
		{
			is = file.getInputStream();
			documentService.upload(userinfo.getUserId(), documentForm.getTitle(), documentForm.getType(), is, file.getOriginalFilename(), file.getSize());
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("upload failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		finally
		{
			IOUtils.closeQuietly(is);
		}
		return formResult;
	}

	/**
	 * 删除指定资料
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public FormResult delete(@PathVariable String id)
	{
		FormResult formResult = new FormResult();
		try
		{
			documentService.delete(id);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("delete failed! id: " + id, e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

}
