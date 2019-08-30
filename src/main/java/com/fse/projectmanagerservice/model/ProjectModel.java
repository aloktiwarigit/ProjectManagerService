package com.fse.projectmanagerservice.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class ProjectModel {

	
	@Getter
	@Setter
	private long projId;
	
	@Getter
	@Setter
	private int noOfTask;
	
	@Getter
	@Setter
	private int completed;
	
	@Getter
	@Setter
	private int priority;
	@Getter
	@Setter
	private long managerId;
	@Getter
	@Setter
	private String projectDesc;
	@Getter
	@Setter
	private Date startDt;
	@Getter
	@Setter
	private Date endDt;
}
