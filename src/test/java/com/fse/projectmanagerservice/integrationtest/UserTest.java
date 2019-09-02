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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.fse.projectmanagerservice.dao.UserDao;
import com.fse.projectmanagerservice.entity.User;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserTest {


    @Autowired
    private MockMvc mockMvc;
    
    private static final ObjectMapper om = new ObjectMapper();
	
	

	@MockBean
    private UserDao mockUserRepository;
	
	@Before
    public void init() {
       User	userTest = new User ();
       userTest.setUserID(1L);
       System.out.println();
	when(mockUserRepository.findById(1L)).thenReturn(Optional.of(userTest));
    }
	 @Test
	    public void find_allUsers_OK() throws Exception {
	    	
	    	User user1 = new User();
	    	user1.setUserID(1L);
	    	user1.setFirstName("Alpha");
	    	
	    	User user2 = new User();
	    	user2.setUserID(2L);
	    	user2.setFirstName("Beta");

	    	
	    	  List<User> userList = Arrays.asList(
	                  user1,
	                  user2);

	          when(mockUserRepository.findAll()).thenReturn(userList);

	          mockMvc.perform(get("/User//getAllUsers"))
	                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	                  .andExpect(status().isOk())
	                  .andExpect(jsonPath("$", hasSize(2)))
	                  .andExpect(jsonPath("$[0].fstName", is("Alpha")))
	                  
	                  ;
	               

	          verify(mockUserRepository, times(1)).findAll();

	    }
	 
	 @Test
	    public void save_user_OK() throws Exception {

	       User user1 = new User ();
	       user1.setUserID(5L);
	       user1.setFirstName("Gamma");
	       
	      
	       when(mockUserRepository.save(any(User.class))).thenReturn(user1);

	        mockMvc.perform(post("/User/addUser")
	                .content(om.writeValueAsString(user1))
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                /*.andDo(print())*/
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.fstName", is("Gamma")));
	               
	        verify(mockUserRepository, times(1)).save(any(User.class));

	    }
	 @Test
	    public void update_user_OK() throws Exception {

	    	 User user1 = new User ();
	         
	    	 user1.setUserID(6L);
	    	 user1.setFirstName("Tiwari");
	    	 user1.setEmployeeID(2L);
	         
	         when(mockUserRepository.save(any(User.class))).thenReturn(user1);

	        mockMvc.perform(put("/User/editUser/6")
	                .content(om.writeValueAsString(user1))
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.userId", is(6)));
	              
	               

	    }

	  @Test
	    public void delete_user_OK() throws Exception {

	        doNothing().when(mockUserRepository).deleteById(1L);

	        mockMvc.perform(delete("/User/deleteUser/1"))
	                /*.andDo(print())*/
	                .andExpect(status().isOk());

	        verify(mockUserRepository, times(1)).deleteById(1L);
	    }
}
