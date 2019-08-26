package com.fse.projectmanagerservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fse.projectmanagerservice.model.UserModel;
import com.fse.projectmanagerservice.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/User")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(path = "/getAllUsers", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> userModelList = userService.getAllUsers();
		return ResponseEntity.ok().header("Custom-Header", "foo").body(userModelList);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	public UserModel addUser(@RequestBody @Valid UserModel userModel) {

		try {
			userModel = userService.addUser(userModel);
		} catch (Exception e) {
			e.printStackTrace();
			return userModel;
		}

		return userModel;
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.PUT, produces = { "application/json" }, consumes = {
			"application/json" })
	public UserModel updateUser(@RequestBody @Valid UserModel userModel,@PathVariable("userId") Long userId) {

		try {
			 userService.updateUser(userId,userModel);
		} catch (Exception e) {
			e.printStackTrace();
			return userModel;
		}

		return userModel;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userId") Long userId) {
		
		userService.deleteUser(userId);

	}

}
