package com.example.JournalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.UsersRepository;

@Component
public class UsersService {
	
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getAll() {
		return usersRepository.findAll();
	}
	
	public Optional<Users> getById(ObjectId id) {
		return usersRepository.findById(id);
	}
	
	public Users saveUsers(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		users.setRoles(Arrays.asList("USER"));
		return usersRepository.save(users);
	}
	
	public void delete(ObjectId id) {
		
		usersRepository.deleteById(id);
	}
	
	public void deleteByUserName(String username) {
		usersRepository.deleteByUserName(username);
	}
	
	public Users findByUserName(String userName) {
			
			return usersRepository.findByUserName(userName);
	}
}
