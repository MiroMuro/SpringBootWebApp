package com.Taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskmanager.Dto.LoginDTO;
import com.Taskmanager.Dto.UserDTO;
import com.Taskmanager.entity.LoginResponse;
import com.Taskmanager.entity.User;
import com.Taskmanager.repository.UserRepository;
@Service("userService")
public interface UserService {
	User addUser(UserDTO userDTO);
	LoginResponse loginUser(LoginDTO loginDto);
}
