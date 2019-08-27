package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	UserDao userDao;
	
	Project projEntity = new Project();
	
	
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
			projModel.setManagerId(project.getUser().getUserID());
			projModel.setProjId(project.getProjectID());
			projModelList.add(projModel);
			
		}
		
		
		
	}
	
	
	private void populateEntityObj(ProjectModel projectModel) 
	{
		
		
		projEntity.setProjectID(projectModel.getProjId());
		projEntity.setPriority(projectModel.getPriority());
		projEntity.setEndDate(projectModel.getEndDt());
		projEntity.setStartDate(projectModel.getStartDt());
		projEntity.setProjectName(projectModel.getProjectDesc());
		projEntity.setUser(userDao.findById(projectModel.getManagerId()).get());
		
		
		
	}
	
	

}
