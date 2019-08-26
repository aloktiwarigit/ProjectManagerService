package com.fse.projectmanagerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fse.projectmanagerservice.entity.Task;

public interface TaskDao extends JpaRepository<Task, Long>{

}
