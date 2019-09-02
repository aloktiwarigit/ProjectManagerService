package com.fse.projectmanagerservice.integrationtest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.projectmanagerservice.dao.TaskDao;
import com.fse.projectmanagerservice.entity.Task;
import com.fse.projectmanagerservice.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskTest {
	@Autowired
	private MockMvc mockMvc;

	private static final ObjectMapper om = new ObjectMapper();

	@MockBean
	private TaskDao mockTaskRepository;

	@Before
	public void init() {
		Task task = new Task();
		task.setTaskID(1L);
		task.setTaskName("Mock Testing");
		when(mockTaskRepository.findById(1L)).thenReturn(Optional.of(task));
	}

	@Test
	public void find_allTask_OK() throws Exception {

		Task task1 = new Task();
		task1.setTaskID(2L);
		task1.setTaskName("Mock2");

		Task task2 = new Task();
		task2.setTaskID(3L);
		task2.setTaskName("Mock3");

		List<Task> taskList = Arrays.asList(task1, task2);

		when(mockTaskRepository.findAll()).thenReturn(taskList);

		mockMvc.perform(get("/Task/getAllTasks")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].taskId", is(2)));

		verify(mockTaskRepository, times(1)).findAll();

	}

	@Test
	public void update_task_OK() throws Exception {

		Task task1 = new Task();
		task1.setTaskID(1L);
		// task1.setTaskName("update");

		when(mockTaskRepository.save(any(Task.class))).thenReturn(task1);

		mockMvc.perform(put("/Task/updateTask/1").content(om.writeValueAsString(task1)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.taskId", is(1)));

	}

	/*
	 * public void delete_Task_OK() throws Exception {
	 * 
	 * doNothing().when(mockTaskRepository).deleteById(1L);
	 * 
	 * mockMvc.perform(delete("/Task/deleteTask/1")) .andDo(print())
	 * .andExpect(status().isOk());
	 * 
	 * verify(mockTaskRepository, times(1)).deleteById(1L); }
	 */
}
