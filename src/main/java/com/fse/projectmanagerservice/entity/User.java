package com.fse.projectmanagerservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	
	@Getter
	@Setter
	@OneToMany( orphanRemoval= true, mappedBy="user" , fetch=FetchType.EAGER)
	private Set<Project> project;
	
	@Getter
	@Setter
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval= true, mappedBy="taskID" , fetch=FetchType.EAGER)
	private Set<Task>  task;
	
	
}
