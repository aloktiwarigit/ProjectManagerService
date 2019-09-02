package com.fse.projectmanagerservice.integrationtest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.entity.Task;

//@WebMvcTest(ProjectController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectTest {
	
	
	private static final ObjectMapper om = new ObjectMapper();
	
	private static final Long ID = Long.valueOf(1001);
	
	
	@MockBean
    private ProjectDao mockProjectRepository;
	
	 @Before
	    public void init() {
	       Project project = new Project ();
	       project.setProjectID(1L);
		when(mockProjectRepository.findById(1L)).thenReturn(Optional.of(project));
	    }
	
	
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void find_allProject_OK() throws Exception {
    	
    	Project proj1 = new Project();
    	proj1.setProjectID(3L);

    	Project proj2 = new Project();
    	proj1.setProjectID(4L);
    	
    	  List<Project> projectList = Arrays.asList(
                  proj1,
                  proj2);

          when(mockProjectRepository.findAll()).thenReturn(projectList);

          mockMvc.perform(get("/Project/getAllProjects"))
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$", hasSize(2)))
                  .andExpect(jsonPath("$[0].projId", is(4)))
                  
                  ;
               

          verify(mockProjectRepository, times(1)).findAll();

    }

    
    @Test
    public void find_allProjectTask_OK() throws Exception {
    	
    	Task task1 = new Task();
    	task1.setTaskID(2L);
    	task1.setTaskName("FindProject1");

    	Task task2 = new Task();
    	task2.setTaskID(3L);
    	task1.setTaskName("FindProject2");
    	  List<Task> taskList = Arrays.asList(
                  task1,
                  task2);
    	  
    	  Set<Task> taskSet = new HashSet<Task>(taskList);
    	  
    	  Project projectTaskAll = new Project();
    	  projectTaskAll.setProjectID(1L);
    	  projectTaskAll.setTaskList(taskSet);
    	  when(mockProjectRepository.findById(1L)).thenReturn(Optional.of(projectTaskAll));
          

          mockMvc.perform(get("/Project/getProjectTasks/1"))
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$", hasSize(2)))
                  .andExpect(jsonPath("$[0].taskId").isNotEmpty());
              }

    
    @Test
    public void save_project_OK() throws Exception {

       Project project = new Project ();
       
       project.setProjectID(5L);
      
       when(mockProjectRepository.save(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/Project/addProject")
                .content(om.writeValueAsString(project))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                /*.andDo(print())*/
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projId", is(5)));
               
        verify(mockProjectRepository, times(1)).save(any(Project.class));

    }
    
    @Test
    public void update_book_OK() throws Exception {

    	 Project updateProject = new Project ();
         
    	 updateProject.setProjectID(6L);;
         
         when(mockProjectRepository.save(any(Project.class))).thenReturn(updateProject);

        mockMvc.perform(put("/Project/updateProject/6")
                .content(om.writeValueAsString(updateProject))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projId", is(6)));
               

    }
    
    @Test
    public void delete_project_OK() throws Exception {

        doNothing().when(mockProjectRepository).deleteById(1L);

        mockMvc.perform(delete("/Project/deleteProject/1"))
                /*.andDo(print())*/
                .andExpect(status().isOk());

        verify(mockProjectRepository, times(1)).deleteById(1L);
    }

}
