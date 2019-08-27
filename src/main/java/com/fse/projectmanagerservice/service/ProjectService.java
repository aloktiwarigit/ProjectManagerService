package com.fse.projectmanagerservice.service;


import java.util.List;

import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.model.ProjectModel;



public interface ProjectService {
	

	public Project addProject(ProjectModel projModel);

	public List<ProjectModel> getAllProjects();
	
	public ProjectModel updateProject(long projectId,ProjectModel projectModel);
	
	public void deleteProject(long projectId);

}
