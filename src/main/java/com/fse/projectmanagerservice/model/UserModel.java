package com.fse.projectmanagerservice.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class UserModel {
	
	
	@Getter
	@Setter
	private long userId;
	
	@Getter
	@Setter
	private String fstName;
	
	@Getter
	@Setter
	private String lstName;
	
	@Getter
	@Setter
	private long empId;
	
}
