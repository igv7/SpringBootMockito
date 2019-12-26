package com.igor.springBootMockito.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.igor.springBootMockito.SpringBootMockitoApplication;
import com.igor.springBootMockito.model.User;
import com.igor.springBootMockito.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootMockitoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class UserControllerIntegrationTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UserService service;
	

	@Test
	void findAllUsersTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUsers")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		assertEquals(0, service.getUsers().size());
	}
	
	@Test
	void findUserByAddressTest() throws Exception {
		String address = "New York";
//		when(service.getUserbyAddress(address))
//		.thenReturn(Stream.of(new User(531, "Kobi", 31, "USA")).collect(Collectors.toList()));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUserByAddress/{address}", address)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
//		verify(service).getUserbyAddress(address); // service is not Mock
//		assertEquals(1, service.getUserbyAddress(address).size());
		
	}
	
	@Test
	void saveUserTest() throws Exception {
		User user = new User(777, "Igor", 36, "IL");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
	}
	
	@Test
	void removeUserTest() throws Exception {
		User user = new User(777, "Igor", 36, "IL");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/remove")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
	}
	
	@Test
	void findUserTest() throws Exception {
//		User user = new User(777, "Igor", 36, "IL");
		User user = mock(User.class);
		System.out.println(user);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUser/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
	}
	
	@Test
	void editUserTest() throws Exception {
		User user = new User(777, "Igor", 36, "IL");
//		User user = mock(User.class);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateUser/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
	}

}
