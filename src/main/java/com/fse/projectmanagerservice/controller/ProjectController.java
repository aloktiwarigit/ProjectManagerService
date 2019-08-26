package com.fse.projectmanagerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.model.ProjectModel;
import com.fse.projectmanagerservice.service.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/Project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	Project projectEntity;
	
	@PostMapping(path = "/addProject") 
	public String updateProject(@RequestBody ProjectModel projectModel) {
		
		System.out.println("Adding Project");
		
		populateEntityObj(projectModel,projectEntity);
		
		projectService.addProject(projectEntity);
		
		return "Saved";
	}

	@GetMapping(path = "/getAllProjects") // Map ONLY GET Requests
	public List<Project> getAllProjects() {
		List<Project> projects = projectService.getAllProjects();
		return projects;
	}

	
	private void populateEntityObj(ProjectModel projectModel, Project projectEntity) 
	{
		projectEntity.setProjectID(projectModel.getProjId());
		projectEntity.setPriority(projectModel.getPriority());
		projectEntity.setEndDate(projectModel.getEndDt());
		projectEntity.setStartDate(projectModel.getStartDt());
		projectEntity.setProjectName(projectModel.getProjectDesc());
		
		
	}

}
