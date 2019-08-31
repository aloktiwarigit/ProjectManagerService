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
@Getter
@Setter
public class ParentTask {
	
	@Id
	@Column(name = "parent_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long parentID;

	@Column(name = "parent_task")
	private String parentTask;
	
		
	

}
