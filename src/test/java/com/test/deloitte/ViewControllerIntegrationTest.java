package com.test.deloitte;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.test.deloitte.controller.ViewController;
import com.test.deloitte.repositories.TaskRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewController.class)
public class ViewControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TaskRepository taskRepository;

	@Test
	public void getIndexAfterLogin() throws Exception {
		mockMvc.perform(get("/index").with(user("user1").password("password"))).andExpect(status().isOk());
		// .andExpect(view().name("index"));
	}

}
