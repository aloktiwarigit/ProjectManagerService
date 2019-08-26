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
	
	
	@Override
	public List<UserModel> getAllUsers() {
		
		List<UserModel> userModelList = new ArrayList<UserModel>();
		populateModelListObj(userModelList, userDao.findAll());
		return userModelList;
	}

	@Override
	public UserModel addUser(UserModel user)
	{
		populateEntityObj(user);
		populateModelObj(user, userDao.save(userEntity));;
		return user;
		
	}
	
	private void populateModelListObj(List<UserModel> userModelList, List<User> userEntity) 
	{
		
		for (User user:userEntity)
		{
			UserModel userModel = new UserModel();
			userModel.setUserId(user.getUserID());
			userModel.setEmpId(user.getEmployeeID());
			userModel.setFstName(user.getFirstName());
			userModel.setLstName(user.getLastName());
			userModelList.add(userModel);
			
		}
		
		
		
	}
	
	private void populateModelObj(UserModel userModel, User userEntity) 
	{
		
		
			userModel = new UserModel();
			userModel.setUserId(userEntity.getUserID());
			userModel.setEmpId(userEntity.getEmployeeID());
			userModel.setFstName(userEntity.getFirstName());
			userModel.setLstName(userEntity.getLastName());
				
	}
	
	private void populateEntityObj(UserModel userModel) 
	{
			userEntity = new User();
			userEntity.setUserID(userModel.getUserId());
			userEntity.setEmployeeID(userModel.getEmpId());
			userEntity.setFirstName(userModel.getFstName());
			userEntity.setLastName(userModel.getLstName());
	}

	@Override
	public void deleteUser(long userId) {
		
		userDao.deleteById(userId);
		
	}

	@Override
	public void updateUser(long userId, UserModel userModel) {
		
		User user = userDao.findById(userId).get();
		populateEntityObj(userModel);
		
		
		userDao.save(userEntity);
		
		
	}
}
