package com.fse.projectmanagerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	@Setter
	@Column(name = "user_id")
	private long userID;
	
	@Getter
	@Setter
	@Column(name = "first_name")
	private String firstName;
	
	@Getter
	@Setter
	@Column(name = "last_name")
	private String lastName;
	
	@Getter
	@Setter
	@Column(name = "employee_id")
	private long employeeID;
	
	
	
	
	
}
