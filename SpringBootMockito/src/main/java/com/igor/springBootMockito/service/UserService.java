package com.igor.springBootMockito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.springBootMockito.dao.UserRepository;
import com.igor.springBootMockito.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	

	public User addUser(User user) {
		return repository.save(user);
	}

	public List<User> getUsers() {
		List<User> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	
	public User getUser(User user) {
		repository.findById(user.getId());
		System.out.println("Success on get User! "+user);
		return user;
	}
	
	public User updateUser(User user) {
		User temp = null;
		Optional<User> optional = repository.findById(user.getId());
		temp = optional.get();
		temp.setAge(user.getAge());
		temp.setAddress(user.getAddress());
		System.out.println("User "+temp.getName()+" was successfully updated! "+temp);
		repository.save(temp);
		return temp;
	}

	public List<User> getUserbyAddress(String address) {
		return repository.findByAddress(address);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}
}
