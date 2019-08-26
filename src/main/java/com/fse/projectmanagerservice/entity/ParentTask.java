package com.fse.projectmanagerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Repository
@Entity
@Table(name="parent_task")
public class ParentTask {
	
	@Id
	@Column(name = "parent_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	@Setter
	private long parentID;

	@Getter
	@Setter
	@Column(name = "parent_task")
	private String parentTask;
	
	
	
	
	

}
