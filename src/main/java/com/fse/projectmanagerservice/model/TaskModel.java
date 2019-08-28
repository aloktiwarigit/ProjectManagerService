package com.fse.projectmanagerservice.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class TaskModel {
	
	
	@Getter
	@Setter
	private long parentId ;
	
	@Getter
	@Setter
	private long userId;
	
	@Getter
	@Setter
	private long projectId;
	
	@Getter
	@Setter
	private int priority ;
	
	@Getter
	@Setter
	private String taskStatus;
	
	@Getter
	@Setter
	private String taskDescription;
	
	@Getter
	@Setter
	private Date startDt;
	
	@Getter
	@Setter
	private Date endDt;
	
	@Getter
	@Setter
	private long taskId;

}
