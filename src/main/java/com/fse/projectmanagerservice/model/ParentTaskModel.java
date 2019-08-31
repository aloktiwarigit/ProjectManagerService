package com.fse.projectmanagerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ParentTaskModel {
	
	private long parentId;
	
	private String parentTask;

}
