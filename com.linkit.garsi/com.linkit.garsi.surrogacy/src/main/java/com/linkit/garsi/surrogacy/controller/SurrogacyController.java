package com.linkit.garsi.surrogacy.controller;

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

import com.linkit.garsi.common.ResourceType;
import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.Attachment;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.common.resource.vo.StateForm;
import com.linkit.garsi.common.utils.RoleUtils;
import com.linkit.garsi.surrogacy.service.SurrogacyCharacteristicsService;
import com.linkit.garsi.surrogacy.service.SurrogacyInfoService;
import com.linkit.garsi.surrogacy.service.SurrogacyLifeStyleService;
import com.linkit.garsi.surrogacy.service.SurrogacyMedicalService;
import com.linkit.garsi.surrogacy.vo.SurrogacyCharacteristics;
import com.linkit.garsi.surrogacy.vo.SurrogacyInfo;
import com.linkit.garsi.surrogacy.vo.SurrogacyLifeStyle;
import com.linkit.garsi.surrogacy.vo.SurrogacyMedicalInfo;
import com.linkit.garsi.surrogacy.vo.SurrogacySearchForm;

/**
 * 代母公司处理代母数据对应的REST接口
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/surrogacy")
@SessionAttributes(Userinfo.KEY)
public class SurrogacyController
{
	@Resource
	private SurrogacyInfoService surrogacyInfoService;
	@Resource
	private SurrogacyLifeStyleService surrogacyLifeStyleService;
	@Resource
	private SurrogacyMedicalService surrogacyMedicalService;
	@Resource
	private SurrogacyCharacteristicsService surrogacyCharacteristicsService;

	@Resource
	private ResourceService resourceService;

	Log log = LogFactory.getLog(getClass());

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
			surrogacyInfoService.uploadAttachment(resourceId, is, file.getOriginalFilename(), file.getSize());
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

	/**
	 * 获取指定ID对应的代母信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
	public FormResult getSurrogacyByResourceId(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyInfo surrogacyInfo = surrogacyInfoService.getSurrogacyByResourceId(resourceId);
			formResult.setData(surrogacyInfo);
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
			log.error("insert failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
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
	public PagingResult<GResource> list(@ModelAttribute Userinfo userinfo, int start, int limit)
	{
		return resourceService.getPagingResourcesByCreator(ResourceType.SURROGACY, userinfo.getUserId(), start, limit);
	}

	/**
	 * 创建代母信息
	 * 
	 * @param surrogacy
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody SurrogacyInfo surrogacy, @ModelAttribute Userinfo userinfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyInfoService.insert(surrogacy, userinfo);
			// 将入库之后的详细信息返回给前端
			formResult.setData(surrogacy);
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
			log.error("insert failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 删除一个代母资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.DELETE)
	public FormResult deleteByResourceId(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyInfoService.deleteByResourceId(resourceId);
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

	/**
	 * 修改代母详细信息
	 * 
	 * @param surrogacy
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public FormResult update(@RequestBody SurrogacyInfo surrogacy)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyInfoService.update(surrogacy);
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
	 * 创建代母的Characteristics
	 * 
	 * @param surrogacyCharacteristics
	 * @return
	 */
	@RequestMapping(value = "characteristics", method = RequestMethod.POST)
	public FormResult createCharacterInfo(@RequestBody SurrogacyCharacteristics surrogacyCharacteristics)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyInfo surrogacyInfo = surrogacyInfoService.getSurrogacyByResourceId(surrogacyCharacteristics.getResourceId());
			if (surrogacyInfo == null)
			{
				formResult.setSuccess(false);
				formResult.setMessage("Can not find SurrogacyInfo with resourceId:" + surrogacyCharacteristics.getResourceId());
			}
			else
			{
				surrogacyCharacteristicsService.insert(surrogacyCharacteristics);
				formResult.setSuccess(true);
				formResult.setData(surrogacyCharacteristics);
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("update failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
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
	 * 创建代母的LifeStyle
	 * 
	 * @param surrogacyCharacteristics
	 * @return
	 */
	@RequestMapping(value = "lifeStyle", method = RequestMethod.POST)
	public FormResult createLifeStyle(@RequestBody SurrogacyLifeStyle surrogacyLifeStyle)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyInfo surrogacyInfo = surrogacyInfoService.getSurrogacyByResourceId(surrogacyLifeStyle.getResourceId());
			if (surrogacyInfo == null)
			{
				formResult.setSuccess(false);
				formResult.setMessage("Can not find SurrogacyInfo with resourceId:" + surrogacyLifeStyle.getResourceId());
			}
			else
			{
				surrogacyLifeStyleService.insert(surrogacyLifeStyle);
				formResult.setSuccess(true);
				formResult.setData(surrogacyLifeStyle);
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("update failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
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
	 * 创建代母的MedicalInfo
	 * 
	 * @param surrogacyCharacteristics
	 * @return
	 */
	@RequestMapping(value = "medicalInfo", method = RequestMethod.POST)
	public FormResult createMedicalInfo(@RequestBody SurrogacyMedicalInfo surrogacyMedicalInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyInfo surrogacyInfo = surrogacyInfoService.getSurrogacyByResourceId(surrogacyMedicalInfo.getResourceId());
			if (surrogacyInfo == null)
			{
				formResult.setSuccess(false);
				formResult.setMessage("Can not find SurrogacyInfo with resourceId:" + surrogacyMedicalInfo.getResourceId());
			}
			else
			{
				surrogacyMedicalService.insert(surrogacyMedicalInfo);
				formResult.setSuccess(true);
				formResult.setData(surrogacyMedicalInfo);
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("update failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
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
	 * 获取characteristics
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/characteristics/{resourceId}", method = RequestMethod.GET)
	public FormResult getCharacteristics(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyCharacteristics surrogacyCharacteristics = surrogacyCharacteristicsService.getByResourceId(resourceId);
			if (surrogacyCharacteristics != null)
			{
				formResult.setSuccess(true);
				formResult.setData(surrogacyCharacteristics);
			}
			else
			{
				formResult.setSuccess(false);
				formResult.setMessage("not exist!");
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("get characteristics failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("get characteristics failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 获取lifeStyle
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/lifeStyle/{resourceId}", method = RequestMethod.GET)
	public FormResult getLifeStyle(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyLifeStyle surrogacyLifeStyle = surrogacyLifeStyleService.getByResourceId(resourceId);
			if (surrogacyLifeStyle != null)
			{
				formResult.setSuccess(true);
				formResult.setData(surrogacyLifeStyle);
			}
			else
			{
				formResult.setSuccess(false);
				formResult.setMessage("not exist!");
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("get surrogacyLifeStyle failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("get surrogacyLifeStyle failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 获取medicalInfo
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/medicalInfo/{resourceId}", method = RequestMethod.GET)
	public FormResult getMedicalInfo(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			SurrogacyMedicalInfo surrogacyMedicalInfo = surrogacyMedicalService.getByResourceId(resourceId);
			if (surrogacyMedicalInfo != null)
			{
				formResult.setSuccess(true);
				formResult.setData(surrogacyMedicalInfo);
			}
			else
			{
				formResult.setSuccess(false);
				formResult.setMessage("not exist!");
			}
		}
		catch (ConstraintViolationException e)
		{
			log.error("get surrogacyMedicalInfo failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("get surrogacyMedicalInfo failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 更新Characteristics
	 * 
	 * @param surrogacyCharacteristics
	 * @return
	 */
	@RequestMapping(value = "characteristics", method = RequestMethod.PUT)
	public FormResult updateCharacteristics(@RequestBody SurrogacyCharacteristics surrogacyCharacteristics)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyCharacteristicsService.update(surrogacyCharacteristics);
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
	 * 更新LifeStyle
	 * 
	 * @param surrogacyLifeStyle
	 * @return
	 */
	@RequestMapping(value = "lifeStyle", method = RequestMethod.PUT)
	public FormResult updateLifeStyle(@RequestBody SurrogacyLifeStyle surrogacyLifeStyle)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyLifeStyleService.update(surrogacyLifeStyle);
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
	 * 更新MedicalInfo
	 * 
	 * @param medicalInfo
	 * @return
	 */
	@RequestMapping(value = "medicalInfo", method = RequestMethod.PUT)
	public FormResult updateMedicalInfo(@RequestBody SurrogacyMedicalInfo medicalInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyMedicalService.update(medicalInfo);
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
	 * 删除characteristics
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/characteristics/{resourceId}", method = RequestMethod.DELETE)
	public FormResult deleteCharacteristics(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyCharacteristicsService.deleteByResourceId(resourceId);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			log.error("delete characteristics failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("delete characteristics failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 删除lifeStyle
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/lifeStyle/{resourceId}", method = RequestMethod.DELETE)
	public FormResult deleteLifeStyle(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyLifeStyleService.deleteByResourceId(resourceId);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			log.error("delete surrogacyLifeStyle failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("delete surrogacyLifeStyle failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 删除medicalInfo
	 * 
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/medicalInfo/{resourceId}", method = RequestMethod.DELETE)
	public FormResult deleteMedicalInfo(@PathVariable String resourceId)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyMedicalService.deleteByResourceId(resourceId);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			log.error("delete surrogacyMedicalInfo failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			log.error("delete surrogacyMedicalInfo failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 查询所有代母信息（只用管理员有权限）
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryList/all", method = RequestMethod.GET)
	public PagingResult<Object> surrogacyQueryListAll(@ModelAttribute Userinfo userinfo,SurrogacySearchForm searchForm, int start, int limit)
	{
		//管理员有权限查询所有代母信息
		if(RoleUtils.isGarsiRole(userinfo)){
			return surrogacyInfoService.getPagingSurrogacyByQueryForm(searchForm, start, limit);
		}else{
			return null;
		}
	}

	/**
	 * 查询该用户创建代母信息
	 * 
	 * @param userinfo
	 *            代母登录用户
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryList/creator", method = RequestMethod.GET)
	public PagingResult<Object> surrogacyQueryListCreator(@ModelAttribute Userinfo userinfo, SurrogacySearchForm searchForm, int start, int limit)
	{
		if(RoleUtils.isGarsiRole(userinfo)){
			//管理员查询
			return surrogacyInfoService.getPagingSurrogacyByQueryForm(searchForm, start, limit);
		}else if(RoleUtils.isCompanyRole(userinfo)){
			//代码公司查询
			return surrogacyInfoService.getPagingSurrogacyByCreatorQueryForm(userinfo.getUserId(), searchForm, start, limit);
		}else{
			return null;
		}
	}

	/**
	 * 查询所有可选代母信息
	 * 
	 * @param searchForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryList/enable", method = RequestMethod.GET)
	public PagingResult<Object> surrogacyQueryListEnable(SurrogacySearchForm searchForm, int start, int limit)
	{
		return surrogacyInfoService.getPagingSurrogacyByQueryFormEnable(searchForm, start, limit);
	}

	/**
	 * 查询顾客选定的代母信息
	 * 
	 * @param userinfo
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/queryList/customer")
	public PagingResult<Object> surrogacyQueryListByCustomerAndName(@ModelAttribute Userinfo userinfo, String name, int start, int limit)
	{
		return surrogacyInfoService.getPagingSurrogacyByCustomerIdAndName(userinfo.getUserId(), name, start, limit);
	}

	/**
	 * 更新流程状态
	 * 
	 * @param resourceId
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "progressState")
	public FormResult modifyProgressState(@RequestParam(value = "resourceId") String resourceId,
			@RequestParam(value = "processState", required = false) String processState)
	{
		FormResult formResult = new FormResult();
		try
		{
			resourceService.updateProcessStateByDetailId(resourceId, processState);
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

	@RequestMapping(value = "/selectResource")
	public FormResult selectResource(@ModelAttribute Userinfo userinfo, @RequestParam(value = "resourceId") String resourceId,
			@RequestParam(value = "resourceState", required = false) String resourceState,
			@RequestParam(value = "processState", required = false) String processState)
	{
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
}
