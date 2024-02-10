package com.Taskmanager;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.List;

import com.Taskmanager.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
//static imports: MockMvcRequestBuilders.*, MockMvcResultMatchers.*
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {
	ObjectMapper objMapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreateTask() throws Exception{
		//Arrange
		Task task = new Task("Task title","Task description",true);
		//Convert Task object to JSON for response content validation.
		String taskJSON = objMapper.writeValueAsString(task);
		//Act
		// *post* and *status* below are STATIC imports, so no need to create an instance of an mvc-whatever.
		ResultActions result = mockMvc.perform(post("/api/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(taskJSON));		
		//Assert 
		//The id is hard coded here, for the id of the task is generated automatically
		//by Jakarta Persistence API in com.Taskmanager.entity.Task
		result.andExpect(status().isCreated())
			  .andExpect(header().string("Location", "http://localhost:8080/api/tasks/4"))
			  .andExpect(jsonPath("$.*", hasSize(4)))
			  .andExpect(jsonPath("$.title").value(task.getTitle()))
			  .andExpect(jsonPath("$.description").value(task.getDescription()))
			  .andExpect(jsonPath("$.completed").value(task.isCompleted()));
			  
		
			   
	}
	@Test
	public void testFetchAllTasks() throws Exception{
		   
		//Act
		ResultActions result = mockMvc.perform(get("/api/tasks").contentType(MediaType.APPLICATION_JSON));
		//Check for the example data inserted by DataLoader.java.
		result.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$",hasSize(3)));
		
	}
}
