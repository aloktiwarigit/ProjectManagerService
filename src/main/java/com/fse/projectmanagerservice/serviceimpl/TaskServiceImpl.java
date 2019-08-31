package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	UserDao userDao;
	
	@Autowired
	ProjectUtils projUtils;
	
	@Autowired
	TaskModel taskModel;
	
	@Override
	public TaskModel addTask(TaskModel task)
	{
		taskEntity = projUtils.populateTaskEntityObj(task, taskEntity);
		taskDao.save(taskEntity);
		return task;
		
	}
	
	public TaskModel getTask(long id)
	{
		TaskModel taskModel = null;
		taskModel = projUtils.populateTaskModelObj(taskModel,taskDao.findById(id).get());
		return taskModel;
	}
	
	public List<TaskModel> getAllTasks()
	{
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		taskModelList = projUtils.populateTaskModelListObj(taskModelList,taskDao.findAll());
		
		return taskModelList;
	}
	
	
	@Override
	public void updateTask(Long taskId, @Valid TaskModel taskModel1) {

		taskEntity = projUtils.populateTaskEntityObj(taskModel1, taskEntity);
		
		taskDao.save(taskEntity);
		
		
	}

	@Override
	public void endTask(@Valid TaskModel taskModel) {
		
		taskModel.setTaskStatus("1");
		taskEntity = projUtils.populateTaskEntityObj(taskModel, taskEntity);
		taskDao.save(taskEntity);
		
	}

	@Override
	public void deleteTask(long id) {
		
		taskDao.deleteById(id);
		
	}
	

}
