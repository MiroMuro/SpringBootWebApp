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
		return taskrepository.save(task);
	};
	
	public List<Task>getAllTasks(){
		return taskrepository.findAll();
	};
	
	public Optional<Task> getTaskById(Long id) {
		return taskrepository.findById(id);
	}
	
	public Task updateTask(Task task) {
		return taskrepository.save(task);
	}
	
	public void deleteTask(Long id) {
		try {
			taskrepository.deleteById(id);
		} catch(IllegalArgumentException e){
			System.out.println(e.toString());
		}
	}
}
