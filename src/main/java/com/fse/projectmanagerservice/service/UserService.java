package com.fse.projectmanagerservice.service;

import java.util.List;


import com.fse.projectmanagerservice.model.UserModel;



public interface UserService {
	
	
	public List<UserModel> getAllUsers();
	
	public UserModel addUser(UserModel userModel);
	
	public void deleteUser(long userId);
	
	public void updateUser(long userId,UserModel userModel);

	
	
	
}
