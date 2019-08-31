package com.fse.projectmanagerservice.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ToString
@Getter
@Setter
public class TaskModel {
	
	
	private long parentId ;
	
	private long userId;
	
	private long projectId;
	
	private int priority ;
	
	private String parentDesc;
	
	private String taskStatus;
	
	private String taskDescription;
	
	private Date startDt;
	
	private Date endDt;
	
	private long taskId;


	
}
