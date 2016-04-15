package com.linkit.garsi.customer.controller;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.linkit.garsi.common.authorize.vo.Userinfo;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.exception.ResourceNotFoundException;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.customer.service.CustomerService;
import com.linkit.garsi.manager.service.AccountService;
import com.linkit.garsi.manager.vo.Customer;
import com.linkit.garsi.manager.vo.EggDemand;
import com.linkit.garsi.manager.vo.ResourceSelectForm;
import com.linkit.garsi.manager.vo.SurrogacyDemand;
import com.linkit.garsi.surrogacy.vo.SurrogacyInfo;

/**
 * 顾客的REST接口
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/customer")
@SessionAttributes(Userinfo.KEY)
public class CustomerController
{
	@Resource
	private CustomerService customerService;
	@Resource
	private AccountService accountService;
	@Resource
	private ResourceService resourceService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 获取当前顾客的指定资源类型的已海选待初定资源
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/resource/{resourceType}/{processState}", method = RequestMethod.GET)
	public GResource[] getAuditionResources(@ModelAttribute Userinfo userinfo, @PathVariable String resourceType, @PathVariable String processState)
	{
		return resourceService.getResourcesByCustomerAndProcessState(resourceType, userinfo.getUserId(), processState);
	}

	/**
	 * 在海选的基础上进行初选
	 * 
	 * @param userinfo
	 * @param resourceSelectForm
	 * @return
	 */
	@RequestMapping(value = "/selection", method = RequestMethod.POST)
	public FormResult selectFreeResources(@ModelAttribute Userinfo userinfo, @RequestBody ResourceSelectForm resourceSelectForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			customerService.selectFreeResources(resourceSelectForm.getResourceType(), userinfo.getUserId(), resourceSelectForm.getResourceIds());
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("selectFreeResources failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 获取经过确认的代母结果
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/surrogacy", method = RequestMethod.GET)
	public SurrogacyInfo getSelectedSurrogacy(@ModelAttribute Userinfo userinfo)
	{
		SurrogacyInfo surrogacy = customerService.getSurrogacyByCustomer(userinfo.getUserId());
		if (surrogacy == null)
		{
			throw new ResourceNotFoundException("Customer: " + userinfo.getUserName() + " Surrogacy is not found!");
		}
		return surrogacy;
	}

	/**
	 * 获取经过确认的代母所在公司的资料集合
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/surrogacy/document", method = RequestMethod.GET)
	public Document[] getSelectedSurrogacyCompanyDocuments(@ModelAttribute Userinfo userinfo)
	{
		Document[] documents = customerService.getSurrogacyCompanyDocumentsByCustomer(userinfo.getUserId());
		if (documents == null)
		{
			throw new ResourceNotFoundException("Customer: " + userinfo.getUserName() + " Surrogacy Documents is not found!");
		}
		return documents;
	}

	/**
	 * 获取顾客信息
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Customer getCustomer(@ModelAttribute Userinfo userinfo)
	{
		return accountService.getCustomerByUserId(userinfo.getUserId());
	}

	/**
	 * 获取顾客的卵子需求
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/demand/egg", method = RequestMethod.GET)
	public EggDemand getEggDemand(@ModelAttribute Userinfo userinfo)
	{
		return customerService.getEggDemandByUserId(userinfo.getUserId());
	}

	/**
	 * 修改卵子需求
	 * 
	 * @param userinfo
	 * @param eggDemand
	 * @return
	 */
	@RequestMapping(value = "/demand/egg", method = RequestMethod.PUT)
	public FormResult updateEggDemand(@ModelAttribute Userinfo userinfo, @RequestBody EggDemand eggDemand)
	{
		FormResult formResult = new FormResult();
		try
		{
			eggDemand.setUserId(userinfo.getUserId());
			customerService.update(eggDemand);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("updateEggDemand failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 获取顾客的精子需求
	 * 
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value = "/demand/surrogacy", method = RequestMethod.GET)
	public SurrogacyDemand getSurrogacyDemand(@ModelAttribute Userinfo userinfo)
	{
		return customerService.getSurrogacyDemandByUserId(userinfo.getUserId());
	}

	/**
	 * 修改代母需求
	 * 
	 * @param userinfo
	 * @param surrogacyDemand
	 * @return
	 */
	@RequestMapping(value = "/demand/surrogacy", method = RequestMethod.PUT)
	public FormResult updateSurrogacyDemand(@ModelAttribute Userinfo userinfo, @RequestBody SurrogacyDemand surrogacyDemand)
	{
		FormResult formResult = new FormResult();
		try
		{
			surrogacyDemand.setUserId(userinfo.getUserId());
			customerService.update(surrogacyDemand);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("updateSurrogacyDemand failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}
}
