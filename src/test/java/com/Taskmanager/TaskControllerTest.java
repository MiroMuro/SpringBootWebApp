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
	@Test
	public void testGetSingleTask() throws Exception{
		//Arrange
		Long taskId = 1L;
		
		//Act
		ResultActions result = mockMvc.perform(get("/api/tasks/{id}",taskId));
		
		//Arrange
		result.andExpect(status().isOk())
		  .andExpect((content().contentType(MediaType.APPLICATION_JSON)))
		  .andExpect(jsonPath("$.*", hasSize(4)))
		  .andExpect(jsonPath("$.id").value(taskId))
		  .andExpect(jsonPath("$.title").value("Take the trashes out!"))
		  .andExpect(jsonPath("$.description").value("The trashes need to be taken out daily"))
		  .andExpect(jsonPath("$.completed").value(false));
		
				  

		
	}
	@Test
	public void testUpdateTask() throws Exception{
		//Arrange
		var modifiedTask = new Task("Clean the trashcans","The trashcans are getting dirty",false);
		String modifiedTaskJson = objMapper.writeValueAsString(modifiedTask);
		Long taskId = 1L;
		
		//Act
		ResultActions result = mockMvc.perform(put("/api/tasks/{id}",taskId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(modifiedTaskJson));
		
	    result.andExpect(status().isOk())
	    	  .andExpect(jsonPath("$.id").value(taskId))
	    	  .andExpect(jsonPath("$.title").value("Clean the trashcans"))
	    	  .andExpect(jsonPath("$.description").value("The trashcans are getting dirty"))
	    	  .andExpect(jsonPath("$.completed").value(false))
	    	  .andDo(print());
	}
	@Test
	public void testUpdatedTaskIsAppended() throws Exception{
		var modifiedTask = new Task("Clean the trashcans","The trashcans are getting dirty",false);
		String modifiedTaskJson = objMapper.writeValueAsString(modifiedTask);
		Long taskId = 1L;
		
		//Act
		ResultActions result = mockMvc.perform(put("/api/tasks/{id}",taskId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(modifiedTaskJson));
		
		ResultActions allTasks = mockMvc.perform(get("/api/tasks"));
		
		//Assert 
		//Updated task is returned
	    result.andExpect(status().isOk())
	    	  .andExpect(jsonPath("$.id").value(taskId))
	    	  .andExpect(jsonPath("$.title").value("Clean the trashcans"))
	    	  .andExpect(jsonPath("$.description").value("The trashcans are getting dirty"))
	    	  .andExpect(jsonPath("$.completed").value(false));
	    //Updated task is appended to the list containing all tasks.
	    allTasks.andExpect(jsonPath("$[0].id").value(1))
	    		.andExpect(jsonPath("$[0].title").value("Clean the trashcans"))
	            .andExpect(jsonPath("$[0].description").value("The trashcans are getting dirty"))
	            .andExpect(jsonPath("$[0].completed").value(false));
	    
	    
	}
	public void testDeleteTask() throws Exception{
		
	}
}
