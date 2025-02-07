package com.example.JournalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
	
	public Users saveNewUsers(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		users.setRoles(Arrays.asList("USER"));
		log.error("New user added!");
		log.warn("New user added!");
		log.info("New user {} added!",users.getUserName());
		log.debug("New user added!");
		log.trace("New user added!");
		return usersRepository.save(users);
	}
	
	public Users saveAdmin(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		users.setRoles(Arrays.asList("USER","ADMIN"));
		return usersRepository.save(users);
	}
	
	public Users saveUsers(Users users) {
		
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
