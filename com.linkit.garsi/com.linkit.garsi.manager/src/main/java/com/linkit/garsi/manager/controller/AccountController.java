package com.linkit.garsi.manager.controller;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.common.rest.FormResult;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.linkit.garsi.manager.service.AccountService;
import com.linkit.garsi.manager.vo.AccountForm;
import com.linkit.garsi.manager.vo.AccountWrapper;

/**
 * Garsi管理员的REST接口
 * 
 * @author wang.sheng
 * 
 */
@RestController
@RequestMapping("/garsi/account")
public class AccountController
{
	@Resource
	private AccountService accountService;

	Log log = LogFactory.getLog(getClass());

	/**
	 * 根据用户名模糊查询账号信息<br>
	 * 账号只限定于代母库,卵子库公司账户和顾客账户. Garsi和超级用户的账号不在其中<br>
	 * 
	 * @param username
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public PagingResult<AccountWrapper> fuzzyFindAccountByUsername(String username, int start, int limit)
	{
		return accountService.fuzzyFindAccountByUsername(username, start, limit);
	}

	/**
	 * 删除指定账号
	 * 
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public FormResult delete(@PathVariable String userId)
	{
		FormResult formResult = new FormResult();
		try
		{
			accountService.delete(userId);
			formResult.setSuccess(true);
		}
		catch (Exception e)
		{
			log.error("deleteAccount failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

	/**
	 * 新建账号
	 * 
	 * @param accountForm
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public FormResult insert(@RequestBody AccountForm accountForm)
	{
		FormResult formResult = new FormResult();
		try
		{
			accountService.insert(accountForm);
			formResult.setSuccess(true);
		}
		catch (ConstraintViolationException e)
		{
			// 前端数据校验错误
			log.error("Account insert failed!", e);
			formResult.copyErrors(e);
			formResult.setMessage("Account insert failed!");
			formResult.setSuccess(false);
		}
		catch (Exception e)
		{
			log.error("Account insert failed!", e);
			formResult.setSuccess(false);
			formResult.setMessage(e.getMessage());
		}
		return formResult;
	}

}
