package com.linkit.garsi.egg.controller;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.linkit.garsi.egg.service.EggEduInfoService;
import com.linkit.garsi.egg.vo.EggEduInfo;

/**
 * 教育信息
 * 
 * @author Administrator
 * 
 */
@RestController
@RequestMapping("/egg/edu")
public class EduInfoController
{

	@Resource
	private EggEduInfoService eduInfoService;

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody EggEduInfo edInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			eduInfoService.insert(edInfo);
			formResult.setData(edInfo);
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
	public FormResult update(@RequestBody EggEduInfo eduInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			eduInfoService.modifyEduInfo(eduInfo);
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
			eduInfoService.deleteEduInfo(id);
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
	 * 获取指定ID对应的教育信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EggEduInfo getEduInfoById(@PathVariable String id)
	{
		return eduInfoService.getEduInfoById(id);
	}

	/**
	 * 获取指定ID对应的教育信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{resourceId}", method = RequestMethod.GET)
	public EggEduInfo[] getAllEduInfoByresourceId(@PathVariable String resourceId)
	{
		return eduInfoService.getAllEduInfoByEgg(resourceId);
	}
}
