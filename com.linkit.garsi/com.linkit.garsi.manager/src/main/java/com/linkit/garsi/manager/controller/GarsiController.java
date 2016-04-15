package com.linkit.garsi.manager.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.linkit.garsi.common.Demand;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.manager.service.GarsiService;
import com.linkit.garsi.manager.vo.CompanyData;
import com.linkit.garsi.manager.vo.EggDemandForm;
import com.linkit.garsi.manager.vo.FinallySelectForm;
import com.linkit.garsi.manager.vo.SurrogacyDemandForm;

/**
 * Garsi管理员的REST接口
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/garsi")
public class GarsiController
{
	@Resource
	private ResourceService resourceService;
	@Resource
	private GarsiService garsiService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 获取顾客指定资源类型的需求内容
	 * 
	 * @param resourceType
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/demand/{resourceType}/customer/{userId}", method = RequestMethod.GET)
	public Demand getCustomerDemand(@PathVariable String resourceType, @PathVariable String userId)
	{
		return garsiService.getDemandByCustomer(resourceType, userId);
	}

	// /**
	// * 为顾客海选资源
	// *
	// * @param userId
	// * @param resourceSelectForm
	// * @return
	// */
	// @RequestMapping(value = "/selected/customer/{userId}", method =
	// RequestMethod.POST)
	// public FormResult selectResources(@PathVariable String userId,
	// @RequestBody ResourceSelectForm resourceSelectForm)
	// {
	// FormResult formResult = new FormResult();
	// try
	// {
	// garsiService.auditionResources(resourceSelectForm.getResourceType(),
	// userId, resourceSelectForm.getResourceIds());
	// formResult.setSuccess(true);
	// }
	// catch (Exception e)
	// {
	// log.error("auditionResources failed!", e);
	// formResult.setSuccess(false);
	// formResult.setMessage(e.getMessage());
	// }
	// return formResult;
	// }

	/**
	 * 为顾客终选资源
	 * 
	 * @param userId
	 * @param finallySelectForm
	 * @return
	 */
	@RequestMapping(value = "/finally/customer/{userId}", method = RequestMethod.POST)
	public FormResult finallySelectResource(@PathVariable String userId, @RequestBody FinallySelectForm finallySelectForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			garsiService.finallySelectResource(finallySelectForm.getResourceType(), userId, finallySelectForm.getResourceId());
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("finallySelectResource failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 根据代母查询表单获取代母资源集合
	 * 
	 * @param surrogacyDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/resource/surrogacy", method = RequestMethod.GET)
	public PagingResult<GResource> searchResources(@RequestBody SurrogacyDemandForm surrogacyDemandForm, int start, int limit)
	{
		return garsiService.searchResources(surrogacyDemandForm, start, limit);
	}

	/**
	 * 根据卵子查询表单获取卵子资源集合
	 * 
	 * @param surrogacyDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/resource/egg", method = RequestMethod.GET)
	public PagingResult<GResource> searchResources(@RequestBody EggDemandForm eggDemandForm, int start, int limit)
	{
		return garsiService.searchResources(eggDemandForm, start, limit);
	}

	/**
	 * 获取顾客资源
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/resource/customer/{userId}", method = RequestMethod.GET)
	public GResource[] getResourcesByCustomer(@PathVariable String userId)
	{
		return resourceService.getResourcesByCustomer(userId);
	}

	/**
	 * 获取顾客资源对应的公司资料集合
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/document/customer/{userId}", method = RequestMethod.GET)
	public CompanyData[] getCompanyDataByCustomer(@PathVariable String userId)
	{
		return garsiService.getCompanyDataByCustomer(userId);
	}

}
