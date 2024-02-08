package com.Taskmanager;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Taskmanager.controllers.TaskController;
import com.Taskmanager.repository.TaskRepository;
import com.Taskmanager.service.TaskService;

@SpringBootTest
public class SmokeTest {
	
	
	@Autowired
	private TaskController taskController;
	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private TaskService taskService;
	
	
	@Test
	void contextLoads() throws Exception{
		assertNotNull(taskController);
		assertNotNull(taskRepo);
		assertNotNull(taskService);
	}
	
	
	
}
