package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ParentTaskDao;
import com.fse.projectmanagerservice.entity.ParentTask;
import com.fse.projectmanagerservice.model.ParentTaskModel;
import com.fse.projectmanagerservice.service.ParentTaskService;

@Service
public class ParentTaskServiceImpl implements ParentTaskService {

	
	ParentTask parentTaskEntity=new ParentTask();
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	
	@Override
	public ParentTaskModel addParentTask(ParentTaskModel parentTaskModel)
	{
		parentTaskEntity.setParentTask(parentTaskModel.getParentTask());
		parentTaskDao.save(parentTaskEntity);
		return parentTaskModel;
		
	}
	
	public List<ParentTaskModel> getAllParentTasks()
	{
		List<ParentTaskModel> parentTaskModelList = new ArrayList<ParentTaskModel>();
		populateModelListObj(parentTaskModelList,parentTaskDao.findAll());
		
		return parentTaskModelList;
	}
	
	private void populateModelListObj(List<ParentTaskModel> parentTaskModelList, List<ParentTask> parentTaskEntity) 
	{
		
		for (ParentTask parentTask:parentTaskEntity)
		{
			ParentTaskModel parentTaskModel = new ParentTaskModel();
			parentTaskModel.setParentId(parentTask.getParentID());
			parentTaskModel.setParentTask(parentTask.getParentTask());
			parentTaskModelList.add(parentTaskModel);
			
		}
		
		
		
	}
	
}
