package com.fse.projectmanagerservice.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.fse.projectmanagerservice.dao.ParentTaskDao;
import com.fse.projectmanagerservice.entity.ParentTask;
import com.fse.projectmanagerservice.model.ParentTaskModel;
import com.fse.projectmanagerservice.serviceimpl.ProjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParentTaskServiceTest {

	@MockBean
    private ParentTaskDao parentTaskDaoMock;
	
	@Autowired
	ProjectUtils projUtils;
	
	@Autowired
	ParentTaskService parentTaskService;
	
	 @Before
	    public void setUp() throws Exception {
	        
	    }
	 
	 @Test
	 public void testGetAllParentTasks()
		{
		 
		 List<ParentTask> parentTaskList =new ArrayList<ParentTask>();
			ParentTask parentTask = new ParentTask();
			parentTask.setParentID(1);
			parentTask.setParentTask("Parent Task");
			parentTaskList.add(parentTask);
		
			when(parentTaskDaoMock.findAll())
			.thenReturn(parentTaskList);
			
		/*
		 * List<ParentTaskModel> parentTaskModelList
		 * =parentTaskService.getAllParentTasks();
		 * System.out.println(parentTaskModelList.get(0).getParentId());
		 */
		assertThat(parentTaskService.getAllParentTasks(), is(notNullValue())); 		
		}
	 
	 @Test
	 public void testAddParentTask() {
	    		when(parentTaskDaoMock.save(any(ParentTask.class)))
	    					.thenReturn(new ParentTask());
	    		
	    		assertThat(parentTaskService.addParentTask(new ParentTaskModel()),is(instanceOf(ParentTaskModel.class)));
		}
	    
}
