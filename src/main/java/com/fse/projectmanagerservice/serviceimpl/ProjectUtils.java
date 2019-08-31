package com.fse.projectmanagerservice.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fse.projectmanagerservice.dao.ParentTaskDao;
import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.ParentTask;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.entity.Task;
import com.fse.projectmanagerservice.entity.User;
import com.fse.projectmanagerservice.model.ParentTaskModel;
import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.model.UserModel;


@Component
public class ProjectUtils {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	@Autowired
	ProjectDao projectDao;
	
	
	public void populateParentTaskModelListObj(List<ParentTaskModel> parentTaskModelList, List<ParentTask> parentTaskEntity) 
	{
		
		for (ParentTask parentTask:parentTaskEntity)
		{
			ParentTaskModel parentTaskModel = new ParentTaskModel();
			parentTaskModel.setParentId(parentTask.getParentID());
			parentTaskModel.setParentTask(parentTask.getParentTask());
			parentTaskModelList.add(parentTaskModel);
			
		}
		
		
		
	}
	
	public List<ProjectModel> populateProjModelListObj(List<ProjectModel> projModelList, List<Project> projEntity) 
	{
		 
		for (Project project:projEntity)
		{
			ProjectModel projModel = new ProjectModel();
			
			projModel.setEndDt(project.getEndDate());
			projModel.setStartDt(project.getStartDate());
			projModel.setPriority(project.getPriority());
			projModel.setProjectDesc(project.getProjectName());
			projModel.setManagerId(project.getUserProject().getUserID());
			projModel.setProjId(project.getProjectID());
			
			List<Task> activeTaskList = project.getTaskList().stream()
					.filter(task -> "1".equals(task.getStatus()))
					.collect(Collectors.toList());
			
			System.out.println("Project:"+ project.getProjectID()+ "activeTaskList:" + activeTaskList.size());
			
			projModel.setCompleted(activeTaskList.size());
	        projModel.setNoOfTask(project.getTaskList().size());
			projModelList.add(projModel);
			
		}
		
		
		return projModelList;
	}
	
	
	public Project populateProjEntityObj(ProjectModel projectModel,Project projEntity) 
	{
		projEntity = new Project();
		projEntity.setPriority(projectModel.getPriority());
		projEntity.setEndDate(projectModel.getEndDt());
		projEntity.setStartDate(projectModel.getStartDt());
		projEntity.setProjectName(projectModel.getProjectDesc());
		if (projectModel.getManagerId()!=0)
			projEntity.setUserProject(userDao.findById(projectModel.getManagerId()).get());
		
		return projEntity;
		
	}

	public List<TaskModel> populateProjTaskModelListObj(List<TaskModel> taskModelList, Set<Task> taskEntity) 
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
		
		return taskModelList;
		
	}
	
	public List<TaskModel> populateTaskModelListObj(List<TaskModel> taskModelList, List<Task> taskEntity) 
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
		
		return taskModelList;
		
	}
	
	public TaskModel populateTaskModelObj(TaskModel taskModel, Task taskEntity) 
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
	
	public Task populateTaskEntityObj(TaskModel taskModel,Task taskEntity) 
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
		return taskEntity;
	}
	
	public List<UserModel>  populateUserModelListObj(List<UserModel> userModelList, List<User> userEntity) 
	{
		
		for (User user:userEntity)
		{
			UserModel userModel = new UserModel();
			userModel.setUserId(user.getUserID());
			userModel.setEmpId(user.getEmployeeID());
			userModel.setFstName(user.getFirstName());
			userModel.setLstName(user.getLastName());
			userModelList.add(userModel);
			
		}
		
		return userModelList;
		
	}
	
	public UserModel populateUserModelObj(UserModel userModel, User userEntity) 
	{
		
		
			userModel = new UserModel();
			userModel.setUserId(userEntity.getUserID());
			userModel.setEmpId(userEntity.getEmployeeID());
			userModel.setFstName(userEntity.getFirstName());
			userModel.setLstName(userEntity.getLastName());
			return userModel;
				
	}
	
	public User populateUserEntityObj(UserModel userModel,User userEntity) 
	{
			userEntity = new User();
			userEntity.setUserID(userModel.getUserId());
			userEntity.setEmployeeID(userModel.getEmpId());
			userEntity.setFirstName(userModel.getFstName());
			userEntity.setLastName(userModel.getLstName());
			return userEntity;
	}



}



