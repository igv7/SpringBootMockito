package com.igor.springBootMockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.springBootMockito.dao.UserRepository;
import com.igor.springBootMockito.model.User;
import com.igor.springBootMockito.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

	
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	
	@Test
	void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(531, "Kobi", 31, "USA"), new User(694, "Matan", 35, "IL")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	void getUserbyAddressTest() {
		String address = "New York";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(531, "Kobi", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	void saveUserTest() {
		User user = new User(680, "Zamir", 33, "Tel Aviv");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	void deleteUserTest() {
		User user = new User(680, "Zamir", 33, "Tel Aviv");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

}
