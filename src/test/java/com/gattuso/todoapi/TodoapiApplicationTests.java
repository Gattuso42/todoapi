package com.gattuso.todoapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gattuso.todoapi.model.Task;
import com.gattuso.todoapi.repository.TaskRepository;
import com.gattuso.todoapi.service.TaskServiceImpl;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class TodoapiApplicationTests {
//
////	Integration Test
//
////	Injected Dependencies
//
//	@Autowired
//	private MockMvc mockMvc;
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//	private TaskRepository taskRepository;
//
//
////	Test Data
//	Task[]tasksDataTest={
//			new Task(1L ,"test1", "testing1", "pending", LocalDate.of(2023, 8, 8)),
//			new Task( 2L,"test2", "testing2", "completed", LocalDate.of(2023, 8, 8)),
//			new Task( 3L,"test3", "testing3", "completed", LocalDate.of(2023, 8, 8)),
//    };
//
//	@BeforeEach
//	public void setupEnvironment(){
//		for (Task task : tasksDataTest) {
//			taskRepository.save(task);
//		}
//	}
//
////	@AfterEach
////	public void clearEnvironment(){
////		taskRepository.deleteAll();
////	}
//
//
//
//
//
////   Test Performs
//
//
//
//	@Test
//	public void getTaskTestSuccessful() throws Exception {
////		Sample
//		Task taskTest = tasksDataTest[1];
////		Mock
////		Request builder
//		RequestBuilder getRequest = MockMvcRequestBuilders.get("/todoapi/task/2")
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(getRequest)
//				.andExpect(status().is2xxSuccessful())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.title").value(taskTest.getTitle()))
//				.andExpect(jsonPath("$.description").value(taskTest.getDescription()))
//				.andExpect(jsonPath("$.status").value(taskTest.getStatus()))
//				.andExpect(jsonPath("$.dueDate").value(String.valueOf(taskTest.getDueDate())));
//
////		Verifications
////		verify(taskRepository,times(1)).findById(1L);
////		Assertions
//	}
//
//	@Test
//	public void getTaskTestUnsuccessful() throws Exception {
////		Sample
////		Request Builder
//		RequestBuilder getRequest = MockMvcRequestBuilders.get("/todoapi/task/40");
////		Perform
//		mockMvc.perform(getRequest)
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	public void saveTaskTestSuccessful() throws Exception {
////		Sample
//		Task taskTest = tasksDataTest[0];
////		Request Builder
//		RequestBuilder postRequest = MockMvcRequestBuilders.post("/todoapi/task")
//				.content(objectMapper.writeValueAsString(taskTest))
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(postRequest)
//				.andExpect(status().isCreated());
//	}
//
//	@Test
//	public void saveTaskUnSuccessful() throws Exception {
////		Sample
//		Task taskTest = tasksDataTest[0];
////		Request Builder
//		RequestBuilder postRequest = MockMvcRequestBuilders.post("/todoapi/task");
////		Perform
//		mockMvc.perform(postRequest)
//				.andExpect(status().is4xxClientError());
//	}
//
//	@Test
//	public void updateTaskSuccessful() throws Exception {
////		Sample
//		Task taskTest = new Task(1L ,"test UPGRADE", "testing1", "pending", LocalDate.of(2023, 8, 8));
//
//
////		Request Builder
//		RequestBuilder putRequest = MockMvcRequestBuilders.put("/todoapi/task/2")
//				.content(objectMapper.writeValueAsString(taskTest))
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(putRequest)
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.title").value("test UPGRADE"));
//	}
//
//	@Test
//	public void updateTaskUnSuccessful() throws Exception {
////		Sample
//		Task taskTest = new Task(1L ,"test1", "testing1", "pending", LocalDate.of(2023, 8, 8));
////		Request Builder
//		RequestBuilder putRequest = MockMvcRequestBuilders.put("/todoapi/task/50")
//				.content(objectMapper.writeValueAsString(taskTest))
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(putRequest)
//				.andExpect(status().isNotFound());
//
//	}
//
//	@Test
//	public void updateTaskStatusSuccessful() throws Exception {
////		Sample
////		Request builder
//		RequestBuilder putRequest = MockMvcRequestBuilders.put("/todoapi/task/status/3/true")
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(putRequest)
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.status").value("completed"));
//
//	}
//
//	@Test
//	public void updateTaskStatusUnSuccessfulNotFound() throws Exception {
////		Sample
////		Request builder
//		RequestBuilder putRequest = MockMvcRequestBuilders.put("/todoapi/task/status/50/true")
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(putRequest)
//				.andExpect(status().isNotFound());
//
//	}
//
//	@Test
//	public void updateTaskStatusUnSuccessfulValidation() throws Exception {
////		Sample
////		Request builder
//		RequestBuilder putRequest = MockMvcRequestBuilders.put("/todoapi/task/status/1/5")
//				.contentType(MediaType.APPLICATION_JSON);
////		Perform
//		mockMvc.perform(putRequest)
//				.andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void deleteTaskSuccessful() throws Exception {
////		Sample
////		Request Builder
//		RequestBuilder deleteRequest = MockMvcRequestBuilders.delete("/todoapi/task/1");
////		Perform
//		mockMvc.perform(deleteRequest)
//				.andExpect(status().isAccepted());
//	}
//
//	@Test
//	public void deleteTaskUnSuccessfulNotFound() throws Exception {
////		Sample
////		Request Builder
//		RequestBuilder deleteRequest = MockMvcRequestBuilders.delete("/todoapi/task/10");
////		Perform
//		mockMvc.perform(deleteRequest)
//				.andExpect(status().isNotFound());
//	}
//
//

}
