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

import com.fse.projectmanagerservice.model.ParentTaskModel;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.service.ParentTaskService;
import com.fse.projectmanagerservice.service.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/Task")
public class TaskController {

	@Autowired
	TaskService taskService;

	@Autowired
	ParentTaskService parentTaskService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	public TaskModel addTask(@RequestBody @Valid TaskModel taskModel) {

		try {
			taskModel = taskService.addTask(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			return taskModel;
		}

		return taskModel;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addParentTask", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	public ParentTaskModel addParentTask(@RequestBody @Valid ParentTaskModel parentTaskModel) {

		try {
			parentTaskModel = parentTaskService.addParentTask(parentTaskModel);
		} catch (Exception e) {
			e.printStackTrace();
			return parentTaskModel;
		}

		return parentTaskModel;
	}

	@RequestMapping(path = "/getAllParentTasks", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<ParentTaskModel>> getAllParentTasks() {
		List<ParentTaskModel> parentTaskModelList = parentTaskService.getAllParentTasks();
		return ResponseEntity.ok().body(parentTaskModelList);

	}

	@RequestMapping(path = "/getAllTasks", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<TaskModel>> getAllTasks() {
		List<TaskModel> TaskModelList = taskService.getAllTasks();
		return ResponseEntity.ok().body(TaskModelList);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/updateTask/{taskId}", method = RequestMethod.PUT, produces = { "application/json" }, consumes = {
			"application/json" })
	public TaskModel updateTask(@RequestBody @Valid TaskModel taskModel,@PathVariable("taskId") Long taskId) {

		try {
			 taskService.updateTask(taskId,taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			return taskModel;
		}

		return taskModel;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/endTask", method = RequestMethod.PUT, produces = { "application/json" }, consumes = {
			"application/json" })
	public TaskModel updateTask(@RequestBody @Valid TaskModel taskModel) {

		try {
			 taskService.endTask(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			return taskModel;
		}

		return taskModel;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteTask/{taskId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("taskId") Long taskId) {
		
		taskService.deleteTask(taskId);

	}
	
	@RequestMapping(path = "/getTask/{id}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<TaskModel> getTask(@PathVariable("id") int id) {
		TaskModel taskModel = taskService.getTask(id);
		System.out.println("Task Desc :"+ taskModel.getTaskDescription());
		return ResponseEntity.ok().header("Custom-Header", "foo").body(taskModel);

	}

}
