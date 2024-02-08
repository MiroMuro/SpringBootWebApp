package com.Taskmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Taskmanager.entity.Task;
public interface TaskRepository extends JpaRepository<Task, Long>{
	

}
