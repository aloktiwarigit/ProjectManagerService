package com.fse.projectmanagerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fse.projectmanagerservice.entity.Project;

public interface ProjectDao extends JpaRepository<Project, Long>{

}
