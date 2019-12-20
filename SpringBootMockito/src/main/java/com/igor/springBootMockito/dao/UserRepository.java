package com.igor.springBootMockito.dao;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.igor.springBootMockito.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{
	
	List<User> findByAddress(String address);

}