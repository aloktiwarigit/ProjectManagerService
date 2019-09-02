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
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/Project")
@Slf4j
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	Project projectEntity;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addProject", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	public ProjectModel addProject(@RequestBody ProjectModel projectModel) {
		log.info("Recieved The Request From UI :" + projectModel.toString());
		projectModel = projectService.addProject(projectModel);
		
		return projectModel;
	}

	@RequestMapping(path = "/getAllProjects", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<ProjectModel>> getAllProjects() {

		log.info("Recieved The Request From UI for getAllProject" );
		List<ProjectModel> projModelList = projectService.getAllProjects();
		log.info("Returning Project Model " + projModelList.toString());
		return ResponseEntity.ok().body(projModelList);

	}

	@RequestMapping(path = "/getProjectTasks/{projID}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<TaskModel>> getProjectTasks(@PathVariable("projID") Long projID) {

		List<TaskModel> taskModelList = projectService.getProjectTasks(projID);
		return ResponseEntity.ok().header("Custom-Header", "foo").body(taskModelList);

	}

	

	@CrossOrigin(origins="http://localhost:4200")@RequestMapping(value="/updateProject/{projectId}",method=RequestMethod.PUT,produces={"application/json"},consumes={"application/json"})

	public  ProjectModel updateProject(@RequestBody @Valid ProjectModel projectModel,@PathVariable("projectId") Long projectId) {

		
			log.info("Project ID Updated:" + projectModel.getProjId());
			projectModel =  projectService.updateProject(projectId,projectModel);
		

		return projectModel;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("projectId") Long projectId) {

		projectService.deleteProject(projectId);

	}

}
