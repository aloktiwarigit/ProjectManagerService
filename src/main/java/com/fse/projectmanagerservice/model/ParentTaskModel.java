package com.fse.projectmanagerservice.model;

import lombok.Getter;
import lombok.Setter;

public class ParentTaskModel {
	
	@Getter
	@Setter
	private long parentId;
	
	@Getter
	@Setter
	private String parentTask;

}
