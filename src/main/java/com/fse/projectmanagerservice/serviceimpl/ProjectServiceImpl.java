package com.fse.projectmanagerservice.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	
	@Autowired
	ProjectDao projectDao;
	
	
	
	@Override
	public Project addProject(Project project) {
		
		return projectDao.save(project);
	}

	@Override
	public List<Project> getAllProjects() {
		
		return projectDao.findAll();
	}

}
