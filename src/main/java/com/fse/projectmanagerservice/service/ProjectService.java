package com.fse.projectmanagerservice.service;


import java.util.List;

import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.model.TaskModel;



public interface ProjectService {
	

	public ProjectModel addProject(ProjectModel projModel);

	public List<ProjectModel> getAllProjects();
	
	public List<TaskModel> getProjectTasks(long projID);
	
	public ProjectModel updateProject(long projectId,ProjectModel projectModel);
	
	public void deleteProject(long projectId);

}
