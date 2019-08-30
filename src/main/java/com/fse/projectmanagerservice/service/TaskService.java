package com.fse.projectmanagerservice.service;

import java.util.List;

import javax.validation.Valid;

import com.fse.projectmanagerservice.model.TaskModel;


public interface TaskService {
	
	public TaskModel addTask(TaskModel taskModel);
	
	public List<TaskModel> getAllTasks();
	
	public TaskModel getTask(long id);

	public void updateTask(Long taskId, @Valid TaskModel taskModel);
	
	public void endTask( @Valid TaskModel taskModel);
	
	public void deleteTask(long id);

}
