package com.Taskmanager.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "id")
public class User {
	
	@Id
	@Column(name="user_id",length=45)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="user_username",nullable = false)
	private String username;
	@Column(name="user_firstname",nullable= false)
	private String firstname;
	@Column(name="user_lastname",nullable = false)
	private String lastname;
	@Column(name="user_password",nullable = false)
	private String password;
	@Column(name="user_email", nullable= false)
	private String email;
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="user", cascade = CascadeType.ALL)
	private List<Task> taskList;
	
	public User() {}
	
	public User(Long id,String firstname, String lastname, String username, String password, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Task> getTasklist() {
		return taskList;
	}
	
	public void setTaskList(List<Task> tasklist) {
		this.taskList = tasklist;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ",\n username=" + username + ",\n firstname=" + firstname + ",\n lastname=" + lastname
				+ ",\n password=" + password + ",\n email=" + email +",\n taskList="+taskList+ "]";
	}

	

	
}
