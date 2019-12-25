package com.igor.springBootMockito.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.igor.springBootMockito.dao.UserRepository;
import com.igor.springBootMockito.model.User;
import com.igor.springBootMockito.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	UserService service;
	
	@MockBean
	UserRepository repository;
	
//	@MockBean
//	User mockUser;
	

	@Test
	void findAllUsersTest() throws Exception {
		when(repository.findAll()).thenReturn(Collections.emptyList());
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUsers")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		verify(repository).findAll();
		assertEquals(0, service.getUsers().size());
	}
	
	@Test
	void findUserByAddressTest() throws Exception {
		String address = "New York";
//		when(service.getUserbyAddress(address))
//		when(repository.findByAddress(address)).thenReturn(Collections.emptyList());
		when(repository.findByAddress(address))
		.thenReturn(Stream.of(new User(531, "Kobi", 31, "USA")).collect(Collectors.toList()));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUserByAddress/{address}", address)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		verify(repository).findByAddress(address);
		assertEquals(1, repository.findByAddress(address).size());
//		verify(service).getUserbyAddress(address); // service is not Mock
//		assertEquals(1, service.getUserbyAddress(address).size());
		
	}
	
	@Test
	void saveUserTest() throws Exception {
		User user = new User(777, "Igor", 36, "IL");
		repository.save(user);
		when(repository.save(user)).thenReturn(user);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		verify(repository, times(1)).save(user);
		assertEquals(user, repository.save(user));
	}
	
	@Test
	void removeUserTest() throws Exception {
		User user = new User(777, "Igor", 36, "IL");
		repository.delete(user);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/remove")
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		verify(repository, times(1)).delete(user);
	}
	
	@Test
	void findUserTest() throws Exception {
//		User user = new User(777, "Igor", 36, "IL");
		User user = mock(User.class);
		Optional<User> temp = repository.findById(user.getId());
		when(repository.findById(user.getId())).thenReturn(temp);
		System.out.println(user);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getUser/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		verify(repository).findById(user.getId());
		assertEquals(temp, repository.findById(user.getId()));
		assertEquals(repository.findById(user.getId()).equals(temp), true);
		verify(repository, times(3)).findById(user.getId());
	}
	
	@Test
	void editUserTest() throws Exception {
//		User user = new User(777, "Igor", 36, "IL");
		User user = mock(User.class);
		repository.save(user);
		when(repository.save(user)).thenReturn(user);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateUser/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result "+mvcResult.getResponse());
		assertEquals(user,  repository.save(user));
		verify(repository, times(2)).save(user);
	}

}
