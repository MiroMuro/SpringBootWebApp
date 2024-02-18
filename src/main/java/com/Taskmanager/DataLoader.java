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
	private UserService userService;
	
	public DataLoader(TaskService taskService) {
		this.taskService = taskService;
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			loadDemoTaskData();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public void loadDemoTaskData() {
		taskService.SaveTask(new Task("Take the trashes out!","The trashes need to be taken out daily",false));
		taskService.SaveTask(new Task("Walk the dogs!","The dogs need to go for a walk multiple times a day",true));
		taskService.SaveTask(new Task("Apply for a job!","You need to apply for a summer job",true));
	}
	
	public void loadDemoUserData() {
		userService.addUser(new UserDTO(1L,"peter","pete@gmail.com","salis"));
	}
	
}
