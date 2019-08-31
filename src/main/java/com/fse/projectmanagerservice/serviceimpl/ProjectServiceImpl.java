package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.dao.TaskDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.entity.Task;
import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	ProjectUtils projUtils;
	
	
	Project projEntity;
	
	Task taskEntity = new Task();
	
	
	@Override
	public Project addProject(ProjectModel projModel) {
		
		projEntity = projUtils.populateProjEntityObj(projModel,projEntity);
		
	    projEntity =  projectDao.save(projEntity);
	    
		
		
		return projEntity;
	}

	@Override
	public List<ProjectModel> getAllProjects() {
		
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		projectModelList = projUtils.populateProjModelListObj(projectModelList, projectDao.findAll());
		return projectModelList;
	}
	
	public ProjectModel updateProject(long projectId,ProjectModel projectModel)
	{
		
		projEntity = projUtils.populateProjEntityObj(projectModel, projEntity);
		projEntity.setProjectID(projectId);
		
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
		catch(Exception sqlEx)
		{
			log.error("Exception Deleteing The Record :" + projectId + "Error Msg :" + sqlEx.getMessage());
		}
		
	}
	
		
	
	@Override
	public List<TaskModel> getProjectTasks(long projID) {
		
		List<TaskModel> taskModelList = new ArrayList<TaskModel>();
		
		projEntity =  projectDao.findById(projID).get();
		Set<Task> taskList =  projEntity.getTaskList();
		taskModelList = projUtils.populateProjTaskModelListObj(taskModelList,taskList);
		return taskModelList;

		
		
	}
	
	

}
