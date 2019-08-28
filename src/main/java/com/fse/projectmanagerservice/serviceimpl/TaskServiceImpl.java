package com.fse.projectmanagerservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ParentTaskDao;
import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.dao.TaskDao;
import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.Task;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	
	Task taskEntity;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	TaskModel taskModel;
	
	@Override
	public TaskModel addTask(TaskModel task)
	{
		populateEntityObj(task);
		taskDao.save(taskEntity);
		return task;
		
	}
	
	private void populateEntityObj(TaskModel taskModel) 
	{
			taskEntity = new Task();
			taskEntity.setEndDate(taskModel.getEndDt());
			taskEntity.setPriority(taskModel.getPriority());
			taskEntity.setStartDate(taskModel.getStartDt());
			taskEntity.setStatus(taskModel.getTaskStatus());
			taskEntity.setTaskName(taskModel.getTaskDescription());
			taskEntity.setUser(userDao.findById(taskModel.getUserId()).get());
			taskEntity.setParentTask(parentTaskDao.findById(taskModel.getParentId()).get());
			taskEntity.setProject(projectDao.findById(taskModel.getProjectId()).get());
	}
	

}
