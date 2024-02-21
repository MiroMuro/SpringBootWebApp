package com.Taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Taskmanager.Dto.UserDTO;
import com.Taskmanager.entity.Task;
import com.Taskmanager.entity.User;
import com.Taskmanager.service.TaskService;
import com.Taskmanager.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {
	//Class used for loading example data into the database upon app start.
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	public DataLoader(TaskService taskService) {
		this.taskService = taskService;
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			loadDemoTaskData();
			//loadDemoUserData();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void loadDemoTaskData() {
		
		User user = userService.addUser(new UserDTO(1L,"Peter","Griffin","petergamer23","pete@gmail.com","salis"));
		taskService.SaveTask(new Task("Take the trashes out!","The trashes need to be taken out daily",false,userService.findUserByID(user.getId())));
		taskService.SaveTask(new Task("Walk the dogs!","The dogs need to go for a walk multiple times a day",false,userService.findUserByID(user.getId())));
		taskService.SaveTask(new Task("Apply for a job!","You need to apply for a summer job",true,userService.findUserByID(user.getId())));
	}
	
	public void loadDemoUserData() {
		userService.addUser(new UserDTO(1L,"Peter","Griffin","petergamer23","pete@gmail.com","salis"));
	}
	
}
