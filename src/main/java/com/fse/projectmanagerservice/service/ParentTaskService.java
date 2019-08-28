package com.fse.projectmanagerservice.service;

import java.util.List;

import com.fse.projectmanagerservice.model.ParentTaskModel;


public interface ParentTaskService {

	
	public ParentTaskModel addParentTask(ParentTaskModel taskModel);
	
	public List<ParentTaskModel> getAllParentTasks();
	
}
