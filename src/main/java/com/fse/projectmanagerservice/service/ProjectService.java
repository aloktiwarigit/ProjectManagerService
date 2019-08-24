package com.fse.projectmanagerservice.service;

import java.util.List;

import com.fse.projectmanagerservice.entity.Project;


public interface ProjectService {
	

	public Project addProject(Project project);

	public List<Project> getAllProjects();

}
