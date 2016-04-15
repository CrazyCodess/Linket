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

import com.linkit.garsi.common.exception.DataValidateException;
import com.linkit.garsi.egg.service.EggFamilyHistoryService;
import com.linkit.garsi.egg.vo.EggFamilyForm;
import com.linkit.garsi.egg.vo.EggFamilyHistory;

/**
 * 生育历史
 * 
 * @author Administrator
 * 
 */
@RestController
@RequestMapping("/egg/familyhistory")
public class FamilyHistoryController
{

	@Resource
	private EggFamilyHistoryService familyHistoryService;

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody EggFamilyForm eggFamilyForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			familyHistoryService.insert(eggFamilyForm);
			formResult.setData(eggFamilyForm);
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
	public FormResult update(@RequestBody EggFamilyForm eggFamilyForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			familyHistoryService.modifyEggFamilyHistory(eggFamilyForm);
			formResult.setSuccess(true);
		}
		catch (DataValidateException e)
		{
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
			familyHistoryService.deleteEggFamilyHistory(id);
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
	 * 获取指定ID对应的生育历史
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EggFamilyHistory getFamilyHistoryById(@PathVariable String id)
	{
		return familyHistoryService.getEggFamilyHistoryById(id);
	}

	/**
	 * 获取指定ID对应的生育历史
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{resourceId}", method = RequestMethod.GET)
	public EggFamilyHistory[] getAllFamilyHistoryByEgg(@PathVariable String resourceId)
	{
		return familyHistoryService.getAllEggFamilyHistoryByEgg(null, resourceId);
	}
}
