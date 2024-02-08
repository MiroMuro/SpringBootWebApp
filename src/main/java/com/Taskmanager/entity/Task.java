package com.Taskmanager.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Table
@Entity
public class Task {
	@Id
	@GeneratedValue
	Long id;
	@Column(nullable= false) 
	String title;
	@Column(nullable= false) 
	String description;
	@Column(nullable= false) 
	boolean completed;
	
	
	
	public Task( String title, String desc, boolean completed) {
		this.title = title;
		this.description = desc;
		this.completed = completed;
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
