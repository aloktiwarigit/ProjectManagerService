package com.fse.projectmanagerservice.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fse.projectmanagerservice.dao.ProjectDao;
import com.fse.projectmanagerservice.entity.Project;
import com.fse.projectmanagerservice.model.ProjectModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectServiceTest {
	

	@MockBean
	ProjectDao projectDao;
	
	
	@Autowired
	ProjectService projectService;
	
	
	
	@Test
	public void testAddProject() {
		
		when(projectDao.save(any(Project.class)))
		.thenReturn(new Project());
		
		assertThat(projectService.addProject(new ProjectModel()),is(instanceOf(Project.class)));
	}
	
	@Test
	public void testGetAllProjects() {
		List<Project> projectList = new ArrayList<Project>();
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setProjectID(1);
		projectList.add(project);
		
		when(projectDao.findAll())
		.thenReturn(projectList);
		
		/* List<ProjectModel> projectModelList = projectService.getAllProjects(); */ 
		assertThat(projectService.getAllProjects(), is(notNullValue()));
		
	}
	
	
	
	
	
	
	
	@Test
	public void testUpdateProject ()
	{
		
	}
	
	}

