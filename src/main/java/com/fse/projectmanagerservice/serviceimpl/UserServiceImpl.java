package com.fse.projectmanagerservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.User;
import com.fse.projectmanagerservice.model.UserModel;
import com.fse.projectmanagerservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	UserDao userDao;
	
	User userEntity;
	
	@Autowired
	UserModel userModel;
	
	@Autowired
	ProjectUtils projUtils;
	
	
	@Override
	public List<UserModel> getAllUsers() {
		
		List<UserModel> userModelList = new ArrayList<UserModel>();
		userModelList = projUtils.populateUserModelListObj(userModelList, userDao.findAll());
		return userModelList;
	}

	@Override
	public UserModel addUser(UserModel user)
	{
		userEntity = projUtils.populateUserEntityObj(user,userEntity);
		user = projUtils.populateUserModelObj(user, userDao.save(userEntity));;
		return user;
		
	}
	
	
	@Override
	public void deleteUser(long userId) {
		
		userDao.deleteById(userId);
		
	}

	@Override
	public void updateUser(long userId, UserModel userModel) {
		
		userModel.setUserId(userId);
		userEntity = projUtils.populateUserEntityObj(userModel,userEntity);
		
		
		userDao.save(userEntity);
		
		
	}
}
