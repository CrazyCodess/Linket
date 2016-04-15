package com.linkit.garsi.manager.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polaris.framework.authorize.service.AuthorizeService;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.authorize.vo.User;
import org.polaris.framework.authorize.vo.UserRoleRel;
import org.polaris.framework.common.rest.FormException;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.RoleType;
import com.linkit.garsi.manager.dao.AccountDao;
import com.linkit.garsi.manager.vo.Account;
import com.linkit.garsi.manager.vo.AccountForm;
import com.linkit.garsi.manager.vo.AccountWrapper;
import com.linkit.garsi.manager.vo.Company;
import com.linkit.garsi.manager.vo.Customer;

/**
 * 账号服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountService
{
	@Resource
	private AccountDao accountDao;
	@Resource
	private AuthorizeService authorizeService;

	Log log = LogFactory.getLog(getClass());

	public PagingResult<Account> findCustomersByUsername(String username, int start, int limit)
	{
		return accountDao.findCustomersByUsername(username, start, limit);
	}

	public PagingResult<AccountWrapper> fuzzyFindAccountByUsername(String username, int start, int limit)
	{
		return accountDao.fuzzyFindAccountByUsername(username, start, limit);
	}

	public Customer getCustomerByUserId(String userId)
	{
		return accountDao.getCustomerByUserId(userId);
	}

	/**
	 * 删除指定账号
	 * 
	 * @param accountId
	 */
	public void delete(String userId)
	{
		authorizeService.deleteUser(userId);
	}

	/**
	 * 添加账户
	 * 
	 * @param accountForm
	 */
	public void insert(AccountForm accountForm)
	{
		Role role = authorizeService.getRole(accountForm.getRoleId());// 暂时只支持一个角色
		if (role == null)
		{
			throw new RuntimeException("Role is not exists! roleId: " + accountForm.getRoleId());
		}
		User user = new User();
		user.setName(accountForm.getUsername());
		user.setPassword(accountForm.getPassword());
		User u = authorizeService.findUserByName(user.getName());
		if (u != null)
		{
			// 该用户已存在
			throw new FormException("UserName: " + user.getName() + " already exists!");
		}
		authorizeService.insert(user);// 添加系统账户
		UserRoleRel userRoleRel = new UserRoleRel();
		userRoleRel.setRoleId(role.getId());
		userRoleRel.setUserId(user.getId());
		authorizeService.insert(userRoleRel);// 添加用户角色关联
		Account account = null;
		if (StringUtils.equals(role.getName(), RoleType.EGG) || StringUtils.equals(role.getName(), RoleType.SPERM)
				|| StringUtils.equals(role.getName(), RoleType.SURROGACY))
		{
			// 公司账号
			Company company = new Company();
			company.setCompanyName(accountForm.getCompanyName());
			account = company;
		}
		else if (StringUtils.equals(role.getName(), RoleType.CUSTOMER))
		{
			// 顾客账号
			Customer customer = new Customer();
			String demandTypeStr = StringUtils.join(accountForm.getDemandTypes(), ',');
			if (demandTypeStr != null && demandTypeStr.length() >= 2)
			{
				customer.setDemandType(demandTypeStr.substring(0, demandTypeStr.length() - 2));
			}
			else
			{
				customer.setDemandType(demandTypeStr);
			}
			Role readonlyRole = authorizeService.getRoleByName("surrogacyReadonly");
			if (readonlyRole != null)
			{
				UserRoleRel rel = new UserRoleRel();
				rel.setUserId(user.getId());
				rel.setRoleId(readonlyRole.getId());
				authorizeService.insert(rel);// 添加代母的只读角色
			}
			else
			{
				log.warn("surrogacyReadonly not found!");
			}
			readonlyRole = authorizeService.getRoleByName("eggReadonly");
			if (readonlyRole != null)
			{
				UserRoleRel rel = new UserRoleRel();
				rel.setUserId(user.getId());
				rel.setRoleId(readonlyRole.getId());
				authorizeService.insert(rel);// 添加卵子的只读角色
			}
			else
			{
				log.warn("surrogacyReadonly not found!");
			}
			account = customer;
		}
		if (account != null)
		{
			// 添加扩展账户
			account.setUserId(user.getId());
			accountDao.insert(account);
		}
	}

}
