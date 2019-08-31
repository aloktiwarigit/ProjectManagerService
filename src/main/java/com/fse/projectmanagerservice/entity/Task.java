package com.fse.projectmanagerservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Repository
@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "task_id")
	private long taskID;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	@Nullable
	private ParentTask parentTask;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
	
	
	@Column(name = "task")
	private String taskName;
	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@Column(name = "end_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "status")
	private String status;
	
}
