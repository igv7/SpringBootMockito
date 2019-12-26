package com.igor.springBootMockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.igor.springBootMockito.service.UserService;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Configuration
public class SpringBootMockitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMockitoApplication.class, args);
		System.out.println("GO! SpringBootMockito!");
	}
	
	//NOT NEEDED FOR UserControllerTest and UserControllerIntegrationTest, BUT NEEDED FOR UserServiceTest
//	@Bean
//	public UserService userService() {
//		return new UserService();
//	}

}
