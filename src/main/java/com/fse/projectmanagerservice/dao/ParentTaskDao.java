package com.fse.projectmanagerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.projectmanagerservice.entity.ParentTask;


public interface ParentTaskDao extends JpaRepository<ParentTask, Long>{

}
