package com.example.JournalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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
		assertNotNull(byUserName);
	}
	
	@Test
	public void getJournalEntriesTest() {
		String username = "Amaya ";
		Users byUserName = usersRepository.findByUserName(username);
		assertTrue(!byUserName.getJournalEntrys().isEmpty());
	}
	
	@ParameterizedTest
	@CsvSource({"2,3,6","2,3,5","3,4,7"})
	public void parameterized(int a, int b, int result) {
		
		assertEquals(result,a+b);
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Ram","Amaya ","Anita","Hari "})
	public void parameterizedUseNameTest(String username) {
		assertNotNull(usersRepository.findByUserName(username));
	}
	@ParameterizedTest
	@ArgumentsSource(UserArgumentProvider.class)
	public void saveUserTest(Users users) {
		assertNotNull(usersService.saveNewUsers(users));
	}
	
}
