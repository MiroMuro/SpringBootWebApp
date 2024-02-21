package com.Taskmanager.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Taskmanager.repository.TaskRepository;
import com.Taskmanager.entity.Task;
@Service
public class TaskService {
	 
	private TaskRepository taskrepository;
	
	@Autowired
	public TaskService(TaskRepository taskrepository) {
		this.taskrepository = taskrepository;
	}
	
	
	//CRUD operations: Create, Read?, Update, Delete
	
	//CREATE
	
	public Task SaveTask(Task task) {
		Task t = taskrepository.save(task);
		System.out.println("Serviceessä"+t);
		return t;
	};
	
	public List<Task>getAllTasks(){
		return taskrepository.findAll();
	};
	
	public Optional<Task> getTaskById(Long id) {
		
		Optional<Task> taskToReturn = java.util.Optional.empty();
		
		if(taskrepository.existsById(id)) {
			return taskToReturn = taskrepository.findById(id);
		}
		
		return taskToReturn;
		
		
	}
	
	public Optional<Task> updateTask(Task task) {

		Optional<Task> taskToReturn = java.util.Optional.empty();
		
		
		if(taskrepository.existsById(task.getId())) {
			taskrepository.save(task);
			
			taskToReturn = Optional.of(task);
			
			return taskToReturn;
		} 
		return taskToReturn;
		
		
		
	}
	
	public boolean deleteTask(Long id) {
			
			if(taskrepository.existsById(id)) {
				taskrepository.deleteById(id);
				return true;
			} 
			return false;
			
			
			
		
	}
}
