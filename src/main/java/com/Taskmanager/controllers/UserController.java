package com.Taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskmanager.Dto.LoginDTO;
import com.Taskmanager.Dto.UserDTO;
import com.Taskmanager.entity.LoginResponse;
import com.Taskmanager.entity.User;
import com.Taskmanager.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/save")
	public String saveUser(@RequestBody UserDTO userDto) {
		String userFromDb = userService.addUser(userDto);
		return userFromDb;
	}
	@PostMapping(value="/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO login){
		LoginResponse loginMessage = userService.loginUser(login);
		return ResponseEntity.ok(loginMessage);
	}
	
}
