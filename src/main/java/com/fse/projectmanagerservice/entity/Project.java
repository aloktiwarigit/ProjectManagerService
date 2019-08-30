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
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	@Setter
	@Column(name = "project_id")
	private long projectID;
	
	@Getter
	@Setter
	@Column(name = "project")
	private String projectName;
	
	@Getter
	@Setter
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;
	
	@Getter
	@Setter
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "end_date")
	private Date endDate;
	
	
	@Getter
	@Setter
	@Column(name = "priority")
	private int priority;
	
		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@Getter
	@Setter
    @JsonIgnore
    private User userProject;
	
	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval= true, mappedBy="project" , fetch=FetchType.EAGER)
	private Set<Task> taskList =new HashSet<Task>();
	
	
	
	
	
}
