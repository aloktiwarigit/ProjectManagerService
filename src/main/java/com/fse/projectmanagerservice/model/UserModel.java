package com.fse.projectmanagerservice.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ToString
@Getter
@Setter
public class UserModel {
	
	
	private long userId;
	
	private String fstName;
	
	private String lstName;
	
	private long empId;

	
	
}
