package com.fse.projectmanagerservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Repository
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "task_id")
	@Getter
	@Setter
	private long taskID;



	@ManyToOne
	@Getter
	@Setter
	@JoinColumn(name = "projectID")
	private Project projectID;
	
	@ManyToOne
	@Getter
	@Setter
	@JoinColumn(name = "userID")
	private User user;
	
	@ManyToOne
	@Getter
	@Setter
	@JoinColumn(name = "parentID")
	private ParentTask parentTask;
	
	@Getter
	@Setter
	@Column(name = "task")
	private String taskName;
	
	@Getter
	@Setter
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Getter
	@Setter
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	@Getter
	@Setter
	@Column(name = "priority")
	private int priority;

	@Getter
	@Setter
	@Column(name = "status")
	private String status;

}
