package com.linkit.garsi.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.Demand;
import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.ResourceType;
import com.linkit.garsi.common.document.service.DocumentService;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.manager.dao.AccountDao;
import com.linkit.garsi.manager.dao.EggDemandDao;
import com.linkit.garsi.manager.dao.GarsiDao;
import com.linkit.garsi.manager.dao.SpermDemandDao;
import com.linkit.garsi.manager.dao.SurrogacyDemandDao;
import com.linkit.garsi.manager.vo.Company;
import com.linkit.garsi.manager.vo.CompanyData;
import com.linkit.garsi.manager.vo.EggDemandForm;
import com.linkit.garsi.manager.vo.SurrogacyDemandForm;

/**
 * Garsi服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GarsiService
{
	@Resource
	private AccountDao accountDao;
	@Resource
	private DocumentService documentService;
	@Resource
	private GarsiDao garsiDao;
	@Resource
	private ResourceService resourceService;
	@Resource
	private EggDemandDao eggDemandDao;
	@Resource
	private SpermDemandDao spermDemandDao;
	@Resource
	private SurrogacyDemandDao surrogacyDemandDao;

	/**
	 * 获取指定资源的顾客需求
	 * 
	 * @param resourceType
	 * @param userId
	 * @return
	 */
	public Demand getDemandByCustomer(String resourceType, String userId)
	{
		if (StringUtils.equals(resourceType, ResourceType.EGG))
		{
			return eggDemandDao.getEggDemandByUserId(userId);
		}
		else if (StringUtils.equals(resourceType, ResourceType.SURROGACY))
		{
			return surrogacyDemandDao.getSurrogacyDemandByUserId(userId);
		}
		else if (StringUtils.equals(resourceType, ResourceType.SPERM))
		{
			return spermDemandDao.getSpermDemandByUserId(userId);
		}
		else
		{
			throw new RuntimeException("UnKnown resourceType: " + resourceType);
		}
	}

	/**
	 * 根据顾客获取公司资料
	 * 
	 * @param userId
	 * @return
	 */
	public CompanyData[] getCompanyDataByCustomer(String userId)
	{
		Company[] companys = accountDao.findCompanysByCustomer(userId);
		List<CompanyData> list = new ArrayList<CompanyData>();
		for (Company company : companys)
		{
			CompanyData companyData = new CompanyData();
			Document[] documents = documentService.getDocumentsByUserId(company.getUserId());
			companyData.setCompany(company);
			companyData.setDocuments(documents);
			list.add(companyData);
		}
		return list.toArray(new CompanyData[0]);
	}

	/**
	 * 根据表单查询条件获取代母集合
	 * 
	 * @param surrogacyDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> searchResources(SurrogacyDemandForm surrogacyDemandForm, int start, int limit)
	{
		return garsiDao.searchResources(surrogacyDemandForm, start, limit);
	}

	/**
	 * 根据表单查询条件获取卵子集合
	 * 
	 * @param eggDemandForm
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> searchResources(EggDemandForm eggDemandForm, int start, int limit)
	{
		return garsiDao.searchResources(eggDemandForm, start, limit);
	}

	// /**
	// * 海选一批资源给指定顾客
	// *
	// * @param resourceType
	// * @param userId
	// * @param resourceIds
	// */
	// public void auditionResources(String resourceType, String userId,
	// String[] resourceIds)
	// {
	// GResource[] resources = resourceService.getResources(resourceType,
	// resourceIds);
	// for (GResource resource : resources)
	// {
	// if (!StringUtils.equals(resource.getResourceState(),
	// ResourceState.ENABLE))
	// {
	// // 资源不可用
	// throw new RuntimeException("Resource: " + resource.getTitle() +
	// " state is disable!");
	// }
	// else if (!StringUtils.equals(resource.getProcessState(),
	// ProcessState.FREE))
	// {
	// // 非自由状态
	// throw new RuntimeException("Resource: " + resource.getTitle() +
	// " process state must be free!");
	// }
	// else if (!StringUtils.equals(resource.getCustomerId(), userId))
	// {
	// // 不可占用别的顾客资源,并且可能是系统受到攻击
	// throw new RuntimeException("Resource: " + resource.getTitle() +
	// " cannot allocate to more than 2 users!");
	// }
	// }
	// // 设置这批资源状态为海选状态
	// resourceService.updateProcessState(resourceIds, ProcessState.AUDITION);
	// }

	/**
	 * 为顾客最后选择资源
	 * 
	 * @param resourceType
	 * @param userId
	 * @param resourceId
	 */
	public void finallySelectResource(String resourceType, String userId, String resourceId)
	{
		// 首先对本次操作的资源进行校验
		GResource resource = resourceService.getResource(resourceId);
		if (resource == null || !StringUtils.equals(resource.getResourceState(), ResourceState.ENABLE))
		{
			// 资源不存在或者当前不可用
			throw new RuntimeException("Resource not exists or resource state is disable!");
		}
		else if (!StringUtils.equals(resource.getProcessState(), ProcessState.SELECTED))
		{
			// 非初定状态
			throw new RuntimeException("Resource process state must be selected!");
		}
		else if (!StringUtils.equals(resource.getCustomerId(), userId))
		{
			// 不可占用别的顾客资源,并且可能是系统受到攻击
			throw new RuntimeException("Resource cannot allocate to more than 2 users!");
		}
		if (StringUtils.equals(resourceType, ResourceType.EGG))
		{
			eggDemandDao.updateResourceId(userId, resourceId);
		}
		else if (StringUtils.equals(resourceType, ResourceType.SPERM))
		{
			spermDemandDao.updateResourceId(userId, resourceId);
		}
		else if (StringUtils.equals(resourceType, ResourceType.SURROGACY))
		{
			surrogacyDemandDao.updateResourceId(userId, resourceId);
		}
		else
		{
			throw new RuntimeException("UnKnown resourceType: " + resourceType);
		}
		Set<String> resourceIdSet = resourceService.getResourceIdSetByCustomer(resourceType, userId);
		resourceIdSet.remove(resourceId);
		// 释放被锁定资源
		resourceService.updateProcessState(resourceIdSet.toArray(new String[0]), ProcessState.FREE);
		// 设置该资源的最终选择状态
		resourceService.updateProcessState(resourceId, ProcessState.FINALLY);
	}

}
