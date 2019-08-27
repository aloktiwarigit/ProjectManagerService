package com.fse.projectmanagerservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	Project projectEntity;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	public ProjectModel addProject(@RequestBody ProjectModel projectModel) {

		System.out.println("Adding Manager ID" + projectModel.getManagerId());
		System.out.println("Adding End Date" + projectModel.getEndDt());
		System.out.println("Adding Start Date" + projectModel.getEndDt());
		System.out.println("Adding Project Desc" + projectModel.getProjectDesc());
		System.out.println("Adding Project priority:" + projectModel.getPriority());

		projectService.addProject(projectModel);

		return projectModel;
	}

	@RequestMapping(path = "/getAllProjects", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<ProjectModel>> getAllUsers() {
		
		
		List<ProjectModel> ProjModelList = projectService.getAllProjects();
		return ResponseEntity.ok().header("Custom-Header", "foo").body(ProjModelList);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/updateProject/{projectId}", method = RequestMethod.PUT, produces = { "application/json" }, consumes = {
			"application/json" })
	public  ProjectModel updateProject(@RequestBody @Valid ProjectModel projectModel,@PathVariable("projectId") Long projectId) {

		try {
			 projectService.updateProject(projectId,projectModel);
		} catch (Exception e) {
			e.printStackTrace();
			return projectModel;
		}

		return projectModel;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("projectId") Long projectId) {
		
		
	projectService.deleteProject(projectId);

	}


}
