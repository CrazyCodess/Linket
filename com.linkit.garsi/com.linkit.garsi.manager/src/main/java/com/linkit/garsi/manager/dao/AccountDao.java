package com.linkit.garsi.manager.dao;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.authorize.vo.Role;
import org.polaris.framework.authorize.vo.User;
import org.polaris.framework.common.dao.HibernateTemplate;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Repository;

import com.linkit.garsi.common.utils.RoleUtils;
import com.linkit.garsi.manager.vo.Account;
import com.linkit.garsi.manager.vo.AccountWrapper;
import com.linkit.garsi.manager.vo.Company;
import com.linkit.garsi.manager.vo.Customer;

/**
 * Account表的DAO
 * 
 * @author wang.sheng
 * 
 */
@Repository
public class AccountDao
{
	@Resource
	private HibernateTemplate hibernateTemplate;

	public void insert(Account account)
	{
		this.hibernateTemplate.save(account);
	}

	public void deleteCustomer(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from Customer t where t.id=?", new Object[] { id });
	}

	public void deleteCompany(String id)
	{
		this.hibernateTemplate.executeUpdate("delete from Company t where t.id=?", new Object[] { id });
	}

	public Customer getCustomer(String id)
	{
		return this.hibernateTemplate.queryForObject("from Customer t where t.id=?", new Object[] { id }, Customer.class);
	}

	public Customer getCustomerByUserId(String userId)
	{
		return this.hibernateTemplate.queryForObject("from Customer t where t.userId=?", new Object[] { userId }, Customer.class);
	}

	public Company getCompany(String id)
	{
		return this.hibernateTemplate.queryForObject("from Company t where t.id=?", new Object[] { id }, Company.class);
	}

	/**
	 * 根据顾客的User ID获取资源所应的公司
	 * 
	 * @param userId
	 * @return
	 */
	public Company[] findCompanysByCustomer(String userId)
	{
		String hql = "from Company t where t.userId in(select r.creatorId from GResource r where r.customerId=?)";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { userId }, Company.class);
	}

	/**
	 * 根据用户名查找顾客
	 * 
	 * @param username
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<Account> findCustomersByUsername(String username, int start, int limit)
	{
		String hql = "from Customer c where c.userId in(select u.id from User u where u.name like ?)";
		Object[] params = new Object[] { "%" + username + "%" };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		Customer[] customers = this.hibernateTemplate.queryForArray(hql, start, limit, params, Customer.class);
		return new PagingResult<Account>(totalCount, customers);
	}

	/**
	 * 根据用户名查找公司
	 * 
	 * @param username
	 * @return
	 */
	public Company[] findCompanysByUsername(String username)
	{
		String hql = "from Company c where c.userId in(select u.id from User u where u.name like ?)";
		return this.hibernateTemplate.queryForArray(hql, new Object[] { "%" + username + "%" }, Company.class);
	}

	public PagingResult<AccountWrapper> fuzzyFindAccountByUsername(String username, int start, int limit)
	{
		String hql = "from User t where t.name<>'admin' and t.name like ? and t.id not in( ";
		hql += "  select rel.userId from Role r, UserRoleRel rel";
		hql += "  where r.id=rel.roleId  and r.name ='garsi') order by t.name";
		Object[] params = new Object[] { "%" + username + "%" };
		long totalCount = this.hibernateTemplate.getTotalCount(hql, params);
		User[] users = this.hibernateTemplate.queryForArray(hql, start, limit, params, User.class);
		AccountWrapper[] wrappers = new AccountWrapper[users.length];
		for (int i = 0; i < users.length; i++)
		{
			User user = users[i];
			AccountWrapper wrapper = new AccountWrapper();
			wrapper.setUserId(user.getId());
			wrapper.setUserName(user.getName());
			Role[] roles = this.hibernateTemplate.queryForArray("from Role t where t.id in(select r.roleId from UserRoleRel r where r.userId=?)",
					new Object[] { user.getId() }, Role.class);
			if (RoleUtils.containsCompanyRole(roles))
			{
				// 公司账号
				Set<String> typeSet = new HashSet<String>();
				for (Role role : roles)
				{
					typeSet.add(role.getName() + " Company");
				}
				wrapper.setUserType(StringUtils.join(typeSet, " , "));
			}
			else if (RoleUtils.containsCustomerRole(roles))
			{
				// 顾客账号
				Customer customer = this.getCustomerByUserId(user.getId());
				if(customer!=null){
					wrapper.setUserType("Customer: " + customer.getDemandType());
				}
				else
				{	
				wrapper.setUserType("UnKnown");
				}
			}
			else
			{
				// 不识别的账号
				wrapper.setUserType("UnKnown");
			}
			wrappers[i] = wrapper;
		}
		return new PagingResult<AccountWrapper>(totalCount, wrappers);
	}
}
