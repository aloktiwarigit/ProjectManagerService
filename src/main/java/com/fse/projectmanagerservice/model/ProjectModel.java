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
public class ProjectModel {

	
	private long projId;
	
	private int noOfTask;
	
	private int completed;
	
	private int priority;
	
	private long managerId;
	
	private String projectDesc;
	
	private Date startDt;
	
	private Date endDt;
	

}
