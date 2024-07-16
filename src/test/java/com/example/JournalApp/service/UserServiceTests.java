package com.example.JournalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.UsersRepository;
import com.example.JournalApp.service.UsersService;

@SpringBootTest
public class UserServiceTests {

	@Autowired
	UsersRepository usersRepository;
	@Autowired
	UsersService usersService;
	
	@Test
	public void add() {
		assertEquals(4, 2+1+1);
	}
	
	@Test
	public void findByUseName() {
		String username = "Ram";
		Users byUserName = usersRepository.findByUserName(username);
		Users byUserName1 = usersService.findByUserName(username);
		assertNotNull(byUserName1);
	}
}
