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
import com.linkit.garsi.egg.service.EggCharacterInfoService;
import com.linkit.garsi.egg.vo.EggCharacterInfo;

/**
 * 行为爱好
 * 
 * @author Administrator
 * 
 */
@RestController
@RequestMapping("/egg/character")
public class CharacterInfoController
{
	@Resource
	private EggCharacterInfoService characterInfoService;

	Log log = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody EggCharacterInfo characterInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			characterInfoService.insert(characterInfo);
			formResult.setData(characterInfo);
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
	public FormResult update(@RequestBody EggCharacterInfo characterInfo)
	{
		FormResult formResult = new FormResult();
		try
		{
			characterInfoService.modifyEggCharacterInfo(characterInfo);
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
			characterInfoService.deleteEggCharacterInfo(id);
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
	 * 获取指定ID对应的行为爱好
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EggCharacterInfo getCharacterInfoById(@PathVariable String id)
	{
		return characterInfoService.getEggCharacterInfoById(id);
	}

	/**
	 * 获取指定ID对应的行为爱好
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/list/{resourceId}", method = RequestMethod.GET)
	public EggCharacterInfo[] getAllCharacterInfoByEgg(@PathVariable String resourceId)
	{
		return characterInfoService.getAllEggCharacterInfoByResourceId(resourceId);
	}
}
