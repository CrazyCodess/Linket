package com.linkit.garsi.egg.controller;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.Attachment;
import com.linkit.garsi.common.resource.vo.StateForm;
import com.linkit.garsi.common.utils.RoleUtils;
import com.linkit.garsi.egg.service.EggService;
import com.linkit.garsi.egg.vo.Egg;
import com.linkit.garsi.egg.vo.EggForm;

@RestController
@RequestMapping("/egg")
@SessionAttributes(Userinfo.KEY)
public class EggController
{
	@Resource
	private EggService eggService;

	Log log = LogFactory.getLog(getClass());
	

	@Resource
	private ResourceService resourceService;

	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody Egg egg ,@ModelAttribute Userinfo userinfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			eggService.insert(egg,userinfo);
			formResult.setData(egg);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			formResult.copyErrors(e);
			formResult.setMessage("Form check failed!");
			formResult.setSuccess(false);
		}
		catch (Exception e)
		{
			log.error("update failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;

	}

	/**
	 * 更新
	 * 
	 * @param Egg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public FormResult update(@RequestBody Egg egg)
	{
		FormResult formResult = new FormResult();
		try
		{
			eggService.modifyEgg(egg);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			formResult.copyErrors(e);
			formResult.setMessage("Form check failed!");
			formResult.setSuccess(false);
		}
		catch (Exception e)
		{
			log.error("update failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public FormResult delete(@PathVariable String id)
	{
		FormResult formResult = new FormResult();
		try
		{
			eggService.deleteEgg(id);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("delete failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 获取指定ID对应的代母信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Egg getEggById(@PathVariable String id)
	{
		return eggService.getEggById(id);
	}

	/**
	 * 获取资源分页列表
	 * 
	 * @param userinfo
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public PagingResult<Object> list(EggForm eggForm,@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		return eggService.getPagingEggByAttr(eggForm, start, limit);
	}

	/**
	 * 查询free状态的egg
	 * 
	 * @param userinfo
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/list/customer")
	public PagingResult<Object> list(@ModelAttribute Userinfo userinfo,EggForm eggForm,@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		eggForm.setProcessState(ProcessState.FREE);
		return eggService.getPagingEggByAttr(eggForm, start, limit);
	}
	
	/**
	 * 查询free状态的且可用的egg
	 * 
	 * @param userinfo
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/list/freeEnableEgg")
	public PagingResult<Object> listFreeEnableEgg(@ModelAttribute Userinfo userinfo,EggForm eggForm,@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		eggForm.setProcessState(ProcessState.FREE);
		eggForm.setResourceState(ResourceState.ENABLE);
		return eggService.getPagingEggByAttr(eggForm, start, limit);
	}
	/**
	 * 查询指定用户选择的egg信息
	 * 
	 * @param userinfo
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/list/myEgg")
	public PagingResult<Object> myEgglist(@ModelAttribute Userinfo userinfo,EggForm eggForm,@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		eggForm.setCustomerId(userinfo.getUserId());
		return eggService.getPagingEggByAttr(eggForm, start, limit);
	}
	
	/**
	 * 查询指定用户创建的egg信息
	 * 
	 * @param userinfo
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/list/myUploadlist")
	public PagingResult<Object> myUploadlist(@ModelAttribute Userinfo userinfo,EggForm eggForm,@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		boolean isGarsi = RoleUtils.isGarsiRole(userinfo);
		if(!isGarsi){
			eggForm.setCreatorId(userinfo.getUserId());
		}
		return eggService.getPagingEggByAttr(eggForm, start, limit);
	}
	
	@RequestMapping(value="/list/getAllCustomerSelect")
	public PagingResult<Object> getAllCustomerSelect(@RequestParam(value = "eggName", required = false) String eggName,
			@RequestParam(value = "surrName", required = false) String surrName,
			@RequestParam(value = "start", required = false,defaultValue="0") Integer start,
			@RequestParam(value = "limit", required = false,defaultValue="10") Integer limit){
		return eggService.getAllCustomerSelect(eggName, surrName, start, limit);
	}
	
	/**
	 * 获取指定资源下的附件列表
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/attachment/{resourceId}", method = RequestMethod.GET)
	public Attachment[] getAttachments(@PathVariable String resourceId)
	{
		return resourceService.getAttachmentsByResourceId(resourceId);
	}
	
	/**
	 * 为指定资源上传附件
	 * 
	 * @param resourceId
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/attachment/{resourceId}", method = RequestMethod.POST)
	public FormResult uploadAttachment(@PathVariable String resourceId, @RequestBody MultipartFile file)
	{
		FormResult formResult = new FormResult();
		InputStream is = null;
		try
		{
			is = file.getInputStream();
			eggService.uploadAttachment(resourceId, is, file.getOriginalFilename(), file.getSize());
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
	 * 删除指定附件
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping(value = "/attachment/{attachmentId}", method = RequestMethod.DELETE)
	public FormResult deleteAttachment(@PathVariable String attachmentId)
	{
		FormResult formResult = new FormResult();
		try
		{
			resourceService.deleteAttachment(attachmentId);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("delete failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}
	
	@RequestMapping(value="/selectResource")
	public FormResult selectResource(@ModelAttribute Userinfo userinfo,
			@RequestParam(value = "resourceId") String resourceId,
			@RequestParam(value = "resourceState", required = false ) String resourceState,
			@RequestParam(value = "processState", required = false ) String processState){
		FormResult formResult = new FormResult();
		try
		{
			resourceService.selectResource(userinfo.getUserId(), resourceId, resourceState, processState);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			log.error("failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 更新流程状态
	 * @param resourceId
	 * @param state
	 * @return
	 */
	@RequestMapping(value="progressState")
	public FormResult modifyProgressState(@RequestParam(value = "resourceId") String resourceId,@RequestParam(value = "processState", required = false ) String processState){
		FormResult formResult = new FormResult();
		try
		{
			resourceService.updateProcessStateByDetailId(resourceId,processState);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			log.error("failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}
	
	/**
	 * 更新使能状态
	 * 
	 * @param stateForm
	 * @return
	 */
	@RequestMapping(value = "/resourceState", method = RequestMethod.PUT)
	public FormResult updateResourceState(@RequestBody StateForm stateForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			resourceService.updateResourceState(stateForm);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("updateResourceState failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}
}
