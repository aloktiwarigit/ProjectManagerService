package com.fse.projectmanagerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fse.projectmanagerservice.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

}
