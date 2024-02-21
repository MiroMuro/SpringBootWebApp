package com.Taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Taskmanager.Dto.LoginDTO;
import com.Taskmanager.Dto.UserDTO;
import com.Taskmanager.entity.LoginResponse;
import com.Taskmanager.entity.User;
import com.Taskmanager.repository.UserRepository;



//Class for implementing the interfaces methods.
@Service
public class UserIMPL implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	//Add an user to the db and return its name.
	@Override
	public User addUser(UserDTO userDTO) {
		User user = new User(
				userDTO.getId(),
				userDTO.getFirstname(),
				userDTO.getLastname(),
				userDTO.getUsername(),
				this.passwordEncoder.encode(userDTO.getPassword()),
				userDTO.getEmail());
		
		userRepo.save(user);
		System.out.println(user.toString());
		return user;
	}
	
	//Function for login validation.
	@Override
	public LoginResponse loginUser(LoginDTO loginDto) {
		//Email validation
		User user1 = userRepo.findByEmail(loginDto.getEmail());
		System.out.println((user1));
		if(user1 != null) {
			//password as text
			String pass = loginDto.getPassword();
			//Encoded password from db
			String encodedPass = user1.getPassword();
			Boolean isPassCorrect = passwordEncoder.matches(pass, encodedPass);
			if(isPassCorrect) {
				Optional<User> user = userRepo.findOneByEmailAndPassword(loginDto.getEmail(), encodedPass);
				if(user.isPresent()) {
					return new LoginResponse("Login success",true);
				} else {
					return new LoginResponse("Login failed", true);
				}
			} else {
				return new LoginResponse("Password is incorrect!",false);
			}
		} else {
			return new LoginResponse("Email doesn't exist!",false);
		}
	}

	@Override
	public User findUserByID(Long id) {
		User user = userRepo.findById(id).orElseThrow(IllegalArgumentException::new);
		
		return user;
	}
		 
}
