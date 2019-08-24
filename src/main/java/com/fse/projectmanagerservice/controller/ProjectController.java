package com.fse.projectmanagerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping(path = "/addProject") 
	public String updateProject(@RequestBody Project project) {
		projectService.addProject(project);
		return "Saved";
	}

	@GetMapping(path = "/getAllProjects") // Map ONLY GET Requests
	public List<Project> getAllProjects() {
		List<Project> projects = projectService.getAllProjects();
		return projects;
	}


}
