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
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	private long userID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "employee_id")
	private long employeeID;
	
	
}
