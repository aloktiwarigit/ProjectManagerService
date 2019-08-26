package com.fse.projectmanagerservice.service;


import java.util.List;

import org.springframework.stereotype.Component;


import com.fse.projectmanagerservice.entity.Project;

@Component
public interface ProjectService {
	

	public Project addProject(Project project);

	public List<Project> getAllProjects();

}
