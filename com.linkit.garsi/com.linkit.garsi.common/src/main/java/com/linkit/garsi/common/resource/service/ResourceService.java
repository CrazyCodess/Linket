package com.linkit.garsi.common.resource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.polaris.framework.common.rest.PagingResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkit.garsi.common.ResourceState;
import com.linkit.garsi.common.resource.dao.AttachmentDao;
import com.linkit.garsi.common.resource.dao.ResourceDao;
import com.linkit.garsi.common.resource.vo.Attachment;
import com.linkit.garsi.common.resource.vo.GResource;
import com.linkit.garsi.common.resource.vo.StateForm;

/**
 * 资源服务
 * 
 * @author wang.sheng
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ResourceService
{
	@Resource
	private ResourceDao resourceDao;
	@Resource
	private AttachmentDao attachmentDao;

	public void insert(Attachment attachment)
	{
		attachmentDao.insert(attachment);
	}

	public Attachment[] getAttachmentsByResourceId(String resourceId)
	{
		return attachmentDao.getAttachmentsByResourceId(resourceId);
	}

	public Attachment[] getAttachmentsByDetailId(String detailId)
	{
		return attachmentDao.getAttachmentsByDetailId(detailId);
	}

	public void deleteAttachment(String attachmentId)
	{
		attachmentDao.delete(attachmentId);
	}

	public void insert(GResource resource)
	{
		resourceDao.insert(resource);
	}

	public GResource getResource(String id)
	{
		return resourceDao.getResource(id);
	}

	public void delete(String id)
	{
		this.resourceDao.delete(id);
		attachmentDao.deleteByResourceId(id);
	}

	public void deleteByDetailId(String detailId)
	{
		resourceDao.deleteByDetailId(detailId);
		attachmentDao.deleteByDetailId(detailId);
	}

	public void updateProcessState(String resourceId, String processState)
	{
		resourceDao.updateProcessState(resourceId, processState);
	}
	
	public void updateProcessStateByDetailId(String resourceId,String state)
	{
		resourceDao.updateProcessStateByDetailId(resourceId,state);
	}

	/**
	 * 选择一个资源改变状态 
	 * @param userId
	 * @param resourceId
	 * @param resourceState
	 */
	public void selectResource(String userId,String resourceId,String resourceState,String processState){
		resourceDao.selectResource(userId, resourceId, resourceState, processState);
	}
	
	public PagingResult<GResource> getPagingResources(String resourceType, int start, int limit)
	{
		return resourceDao.getPagingResources(resourceType, start, limit);
	}

	/**
	 * 获取创建者所辖的资源类型对应的资源集合
	 * 
	 * @param resourceType
	 * @param creatorId
	 * @param start
	 * @param limit
	 * @return
	 */
	public PagingResult<GResource> getPagingResourcesByCreator(String resourceType, String creatorId, int start, int limit)
	{
		return resourceDao.getPagingResourcesByCreator(resourceType, creatorId, start, limit);
	}

	public PagingResult<GResource> getPagingFreeResources(String resourceType, int start, int limit)
	{
		return resourceDao.getPagingFreeResources(resourceType, start, limit);
	}

	public PagingResult<GResource> getPagingFreeEnableResources(String resourceType, int start, int limit)
	{
		return resourceDao.getPagingFreeEnableResources(resourceType, start, limit);
	}

	public GResource[] getResourcesByCustomer(String userId)
	{
		return resourceDao.getResourcesByCustomer(userId);
	}

	public GResource[] getResourcesByCustomerAndProcessState(String resourceType, String userId, String processState)
	{
		GResource[] resources = resourceDao.getResourcesByCustomer(resourceType, userId);
		List<GResource> list = new ArrayList<GResource>();
		for (GResource resource : resources)
		{
			if (StringUtils.equals(resource.getProcessState(), processState) && StringUtils.equals(resource.getResourceState(), ResourceState.ENABLE))
			{
				// 当前是已海选并且可用
				list.add(resource);
			}
		}
		return list.toArray(new GResource[0]);
	}

	/**
	 * 获取被顾客锁定的资源
	 * 
	 * @param resourceType
	 * @param userId
	 * @return
	 */
	public GResource[] getResourcesByCustomer(String resourceType, String userId)
	{
		return resourceDao.getResourcesByCustomer(resourceType, userId);
	}

	public GResource[] getResources(String resourceType, String[] ids)
	{
		return resourceDao.getResources(resourceType, ids);
	}

	/**
	 * 获取被顾客锁定的资源ID集合
	 * 
	 * @param resourceType
	 * @param userId
	 * @return
	 */
	public Set<String> getResourceIdSetByCustomer(String resourceType, String userId)
	{
		return resourceDao.getResourceIdSetByCustomer(resourceType, userId);
	}

	public void updateProcessState(String[] resourceIds, String processState)
	{
		this.resourceDao.updateProcessState(resourceIds, processState);
	}

	public void updateResourceState(String resourceId, String resourceState)
	{
		this.resourceDao.updateResourceState(resourceId, resourceState);
	}

	/**
	 * 批量更新流程状态
	 * 
	 * @param stateForms
	 */
	public void updateProcessState(StateForm stateForm)
	{
		resourceDao.updateProcessState(stateForm.getResourceId(), stateForm.getState());
	}

	/**
	 * 更新资源状态
	 * 
	 * @param stateForms
	 */
	public void updateResourceState(StateForm stateForm)
	{
		resourceDao.updateResourceStateByDetailId(stateForm.getResourceId(), stateForm.getState());
	}
}
