package com.fse.projectmanagerservice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="project")
@Getter
@Setter
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "project_id")
	private long projectID;
	
	@Column(name = "project")
	private String projectName;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private Date endDate;
	
	
	@Column(name = "priority")
	private int priority;
	
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private User userProject;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval= true, mappedBy="project" , fetch=FetchType.EAGER)
	private Set<Task> taskList =new HashSet<Task>();
	
	
	
	
	
}
