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
import com.linkit.garsi.egg.service.EggDonatedAskService;
import com.linkit.garsi.egg.vo.EggAsForm;
import com.linkit.garsi.egg.vo.EggAsk;

/**
 * 家庭成员
 * 
 * @author Administrator
 * 
 */
@RestController
@RequestMapping("/egg/donateask")
public class DonateAskController
{

	@Resource
	private EggDonatedAskService donatedAskService;

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody EggAsForm eggAskForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			donatedAskService.insert(eggAskForm);
			formResult.setData(eggAskForm);
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
	public FormResult update(@RequestBody EggAsForm eggAsForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			donatedAskService.modifyEggDonatedAskRecord(eggAsForm);
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
	 * 获取指定ID对应的问答
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{askId}", method = RequestMethod.GET)
	public EggAsk getEggDonatedRecordById(@PathVariable String askId)
	{
		return donatedAskService.getEggDonatedAskById(askId);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param attachmentId
	 * @return
	 */
	@RequestMapping(value = "/{askId}", method = RequestMethod.DELETE)
	public FormResult delete(@PathVariable String askId)
	{
		FormResult formResult = new FormResult();
		try
		{
			donatedAskService.deleteEggDonatedAskAndRecord(askId);
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
	 * 获取指定ID对应的家庭成员
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{resourceId}", method = RequestMethod.GET)
	public EggAsk[] getAllEggDonatedRecordByResourceId(@PathVariable String resourceId)
	{
		return donatedAskService.getAllEggDonatedRecordByResourceId(resourceId);
	}
}
