package com.Taskmanager.Dto;

import java.util.List;

import com.Taskmanager.entity.Task;

public class UserDTO {
	
	private long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private List<Task> taskList;
	public UserDTO(long userId, String firstname, String lastname, String username, String email, String password) {
		super();
		this.id = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	
	public UserDTO() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTasklist() {
		return taskList;
	}
	
	public void setTaskList(List<Task> tasklist) {
		this.taskList = tasklist;
	}

	
	
	
}
