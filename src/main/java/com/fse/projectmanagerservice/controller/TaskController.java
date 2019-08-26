package com.fse.projectmanagerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	

}
