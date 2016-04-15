package com.linkit.garsi.customer.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.ProcessState;
import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.document.service.DocumentService;
import com.linkit.garsi.common.document.vo.Document;
import com.linkit.garsi.common.resource.service.ResourceService;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.manager.dao.EggDemandDao;
import com.linkit.garsi.manager.dao.SpermDemandDao;
import com.linkit.garsi.manager.dao.SurrogacyDemandDao;
import com.linkit.garsi.manager.vo.EggDemand;
import com.linkit.garsi.manager.vo.SpermDemand;
import com.linkit.garsi.manager.vo.SurrogacyDemand;
import com.linkit.garsi.surrogacy.service.SurrogacyInfoService;
import com.linkit.garsi.surrogacy.vo.SurrogacyInfo;

/**
 * 顾客的服务接口
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerService
{
	@Resource
	private EggDemandDao eggDemandDao;
	@Resource
	private SpermDemandDao spermDemandDao;
	@Resource
	private SurrogacyDemandDao surrogacyDemandDao;
	@Resource
	private ResourceService resourceService;
	@Resource
	private SurrogacyInfoService surrogacyInfoService;
	@Resource
	private DocumentService documentService;

	/**
	 * 根据顾客ID获取已选中代母的
	 * 
	 * @param userId
	 * @return
	 */
	public GResource getSurrogacyResourceByCustomer(String userId)
	{
		SurrogacyDemand surrogacyDemand = this.getSurrogacyDemandByUserId(userId);
		String resourceId = surrogacyDemand.getResourceId();
		if (StringUtils.isEmpty(resourceId))
		{
			return null;
		}
		return resourceService.getResource(resourceId);
	}

	/**
	 * 获取
	 * 
	 * @param userId
	 * @return
	 */
	public Document[] getSurrogacyCompanyDocumentsByCustomer(String userId)
	{
		GResource resource = this.getSurrogacyResourceByCustomer(userId);
		if (resource == null)
		{
			return null;
		}
		String creatorId = resource.getCreatorId();
		return documentService.getDocumentsByUserId(creatorId);
	}

	/**
	 * 根据顾客的用户ID获取代母详细信息
	 * 
	 * @param userId
	 * @return
	 */
	public SurrogacyInfo getSurrogacyByCustomer(String userId)
	{
		GResource resource = this.getSurrogacyResourceByCustomer(userId);
		if (resource == null)
		{
			return null;
		}
		return surrogacyInfoService.getSurrogacyByResourceId(resource.getId());
	}

	/**
	 * 从海选状态的资源中选择一部分资源进行下一个步骤
	 * 
	 * @param resourceType
	 * @param userId
	 * @param resourceIds
	 */
	public void selectFreeResources(String resourceType, String userId, String[] resourceIds)
	{
		GResource[] resources = resourceService.getResourcesByCustomer(resourceType, userId);
		Map<String, GResource> resourceMap = new HashMap<String, GResource>();
		for (GResource resource : resources)
		{
			resourceMap.put(resource.getId(), resource);
		}
		// 校验资源状态
		for (String resourceId : resourceIds)
		{
			GResource resource = resourceMap.remove(resourceId);
			if (resource == null || !StringUtils.equals(resource.getResourceState(), ResourceState.ENABLE))
			{
				// 资源不存在,或者资源不可用
				throw new RuntimeException("Resource: " + resourceId + " not exists or resource state is disable!");
			}
			else if (!StringUtils.equals(resource.getProcessState(), ProcessState.FREE))
			{
				// 非选择状态
				throw new RuntimeException("Resource: " + resourceId + " process state must be free!");
			}
			else if (!StringUtils.equals(resource.getCustomerId(), userId))
			{
				// 不可占用别的顾客资源,并且可能是系统受到攻击
				throw new RuntimeException("Resource: " + resourceId + " cannot allocate to more than 2 users!");
			}
		}
		// resourceMap剩余的资源需要释放
		resourceService.updateProcessState(resourceMap.keySet().toArray(new String[0]), ProcessState.FREE);
		// 设置该批资源的海选状态
		resourceService.updateProcessState(resourceIds, ProcessState.SELECTED);
	}

	public EggDemand getEggDemandByUserId(String userId)
	{
		EggDemand demand = eggDemandDao.getEggDemandByUserId(userId);
		if (demand == null)
		{
			demand = new EggDemand();
			demand.setUserId(userId);
			eggDemandDao.insert(demand);
		}
		return demand;
	}

	public void update(EggDemand demand)
	{
		this.eggDemandDao.update(demand);
	}

	public SpermDemand getSpermDemandByUserId(String userId)
	{
		SpermDemand demand = spermDemandDao.getSpermDemandByUserId(userId);
		if (demand == null)
		{
			demand = new SpermDemand();
			demand.setUserId(userId);
			spermDemandDao.insert(demand);
		}
		return demand;
	}

	public void update(SpermDemand demand)
	{
		this.spermDemandDao.update(demand);
	}

	public SurrogacyDemand getSurrogacyDemandByUserId(String userId)
	{
		SurrogacyDemand demand = surrogacyDemandDao.getSurrogacyDemandByUserId(userId);
		if (demand == null)
		{
			demand = new SurrogacyDemand();
			demand.setUserId(userId);
			surrogacyDemandDao.insert(demand);
		}
		return demand;
	}

	public void update(SurrogacyDemand demand)
	{
		this.surrogacyDemandDao.update(demand);
	}
}
