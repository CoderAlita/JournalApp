package com.example.JournalApp.service;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public List<Users> getAll() {
		return usersRepository.findAll();
	}
	
	public Optional<Users> getById(ObjectId id) {
		return usersRepository.findById(id);
	}
	
	public Users saveUsers(Users users) {
		
		return usersRepository.save(users);
	}
	
	public void delete(ObjectId id) {
		
		usersRepository.deleteById(id);
	}
	
	public Users findByUserName(String userName) {
			
			return usersRepository.findByUserName(userName);
	}
}
