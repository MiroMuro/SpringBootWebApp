package com.Taskmanager.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Table
@Entity
public class Task {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable= false) 
	private String title;
	@Column(nullable= false) 
	private String description;
	@Column(nullable= false) 
	private boolean completed;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable= false, name="user_id")
	private User user;
	
	public Task() {
		
	}
	public Task( String title, String desc, boolean completed, User user ) {
		this.title = title;
		this.description = desc;
		this.completed = completed;
		this.user = user;
	}
	
	public Task(long l, String string, String string2, boolean b,User user) {
		this.id = l;
		this.title = string;
		this.description = string2;
		this.completed = b;
		this.user = user;
		// TODO Auto-generated constructor stub
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", completed=" + completed
				+ "]";
	}
	
}
