package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
			if (taskModel.getTaskId() != 0)
				taskEntity.setTaskID(taskModel.getTaskId());
			taskEntity.setEndDate(taskModel.getEndDt());
			taskEntity.setPriority(taskModel.getPriority());
			taskEntity.setStartDate(taskModel.getStartDt());
			taskEntity.setStatus(taskModel.getTaskStatus());
			taskEntity.setTaskName(taskModel.getTaskDescription());
			if (taskModel.getParentId()!=0)
				taskEntity.setParentTask(parentTaskDao.findById(taskModel.getParentId()).get());
			taskEntity.setProject(projectDao.findById(taskModel.getProjectId()).get());
			taskEntity.setUser(userDao.findById(taskModel.getUserId()).get());
			
	}
	
	public TaskModel getTask(long id)
	{
		TaskModel taskModel = null;
		taskModel = populateModelObj(taskModel,taskDao.findById(id).get());
		return taskModel;
	}
	
	public List<TaskModel> getAllTasks()
	{
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		populateModelListObj(taskModelList,taskDao.findAll());
		
		return taskModelList;
	}
	
	private void populateModelListObj(List<TaskModel> taskModelList, List<Task> taskEntity) 
	{
		
		for (Task task:taskEntity)
		{
			TaskModel taskModel = new TaskModel();
			if (task.getParentTask()!=null)
				taskModel.setParentId(task.getParentTask().getParentID());
			taskModel.setEndDt(task.getEndDate());
			taskModel.setPriority(task.getPriority());
			taskModel.setProjectId(task.getProject().getProjectID());
			taskModel.setStartDt(task.getStartDate());
			taskModel.setTaskDescription(task.getTaskName());
			taskModel.setTaskId(task.getTaskID());
			taskModel.setTaskStatus(task.getStatus());
			taskModel.setUserId(task.getUser().getUserID());
			if (task.getParentTask()!=null)
				taskModel.setParentDesc(task.getParentTask().getParentTask());
			
			taskModelList.add(taskModel);
			
		}
		
		
		
	}
	
	private TaskModel populateModelObj(TaskModel taskModel, Task taskEntity) 
	{
		taskModel = new TaskModel();
		if (taskEntity.getParentTask()!=null)
			taskModel.setParentId(taskEntity.getParentTask().getParentID());
		taskModel.setEndDt(taskEntity.getEndDate());
		taskModel.setPriority(taskEntity.getPriority());
		taskModel.setProjectId(taskEntity.getProject().getProjectID());
		taskModel.setStartDt(taskEntity.getStartDate());
		taskModel.setTaskDescription(taskEntity.getTaskName());
		taskModel.setTaskId(taskEntity.getTaskID());
		taskModel.setTaskStatus(taskEntity.getStatus());
		taskModel.setUserId(taskEntity.getUser().getUserID());
		
		return taskModel;
					
	}

	@Override
	public void updateTask(Long taskId, @Valid TaskModel taskModel1) {

		populateEntityObj(taskModel1);
		
		taskDao.save(taskEntity);
		
		
	}

	@Override
	public void endTask(@Valid TaskModel taskModel) {
		
		taskModel.setTaskStatus("1");
		populateEntityObj(taskModel);
		taskDao.save(taskEntity);
		
	}

	@Override
	public void deleteTask(long id) {
		
		taskDao.deleteById(id);
		
	}
	

}
