package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.dao.TaskDao;
import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.entity.Task;
import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	UserDao userDao;
	
	Project projEntity;
	
	Task taskEntity = new Task();
	
	
	@Override
	public Project addProject(ProjectModel projModel) {
		
		populateEntityObj(projModel);
		
	    projEntity =  projectDao.save(projEntity);
	    
		
		
		return projEntity;
	}

	@Override
	public List<ProjectModel> getAllProjects() {
		
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		populateModelListObj(projectModelList, projectDao.findAll());
		return projectModelList;
	}
	
	public ProjectModel updateProject(long projectId,ProjectModel projectModel)
	{
		
		populateEntityObj(projectModel);
		
		
		projectDao.save(projEntity);
		return projectModel;
	}

	@Override
	public void deleteProject(long projectId) {
		
		System.out.println("Deleting Project ID :" + projectId);
		
		try
		{
		    projectDao.deleteById(projectId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void populateModelListObj(List<ProjectModel> projModelList, List<Project> projEntity) 
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
		
		
		
	}
	
	
	private void populateEntityObj(ProjectModel projectModel) 
	{
		projEntity = new Project();
		projEntity.setProjectID(projectModel.getProjId());
		projEntity.setPriority(projectModel.getPriority());
		projEntity.setEndDate(projectModel.getEndDt());
		projEntity.setStartDate(projectModel.getStartDt());
		projEntity.setProjectName(projectModel.getProjectDesc());
		projEntity.setUserProject(userDao.findById(projectModel.getManagerId()).get());
		
		
		
	}

	private void populateTaskModelListObj(List<TaskModel> taskModelList, Set<Task> taskEntity) 
	{
		
		for (Task task:taskEntity)
		{
			TaskModel taskModel = new TaskModel();
			taskModel.setParentId(task.getParentTask().getParentID());
			taskModel.setEndDt(task.getEndDate());
			taskModel.setPriority(task.getPriority());
			taskModel.setProjectId(task.getProject().getProjectID());
			taskModel.setStartDt(task.getStartDate());
			taskModel.setTaskDescription(task.getTaskName());
			taskModel.setTaskId(task.getTaskID());
			taskModel.setTaskStatus(task.getStatus());
			taskModel.setUserId(task.getUser().getUserID());
			taskModel.setParentDesc(task.getParentTask().getParentTask());
			
			taskModelList.add(taskModel);
			
		}
		
		
		
	}
	
	
	@Override
	public List<TaskModel> getProjectTasks(long projID) {
		
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		
		projEntity =  projectDao.findById(projID).get();
		Set<Task> taskList =  projEntity.getTaskList();
		populateTaskModelListObj(taskModelList,taskList);
		return taskModelList;

		
		
	}
	
	

}
