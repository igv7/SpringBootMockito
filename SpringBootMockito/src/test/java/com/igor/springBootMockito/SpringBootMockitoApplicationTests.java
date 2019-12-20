package com.igor.springBootMockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.springBootMockito.service.UserService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Configuration
class SpringBootMockitoApplicationTests {
	
//	@Bean
//	public UserService userService() {
//		return new UserService();
//	}
	
	@Test
	void contextLoads() {
	}

}
