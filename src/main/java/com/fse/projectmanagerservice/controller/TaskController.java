package com.fse.projectmanagerservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projectmanagerservice.model.ParentTaskModel;
import com.fse.projectmanagerservice.model.TaskModel;
import com.fse.projectmanagerservice.model.UserModel;
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
		return ResponseEntity.ok().header("Custom-Header", "foo").body(parentTaskModelList);

	}

}
