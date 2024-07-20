package com.example.JournalApp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.UsersRepository;
import com.example.JournalApp.service.UserDetailsServiceImpl;



public class UserDetailsServiceImplTest {
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Mock
	UsersRepository usersRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void UserDetailsServiceImplTest() {
		when(usersRepository.findByUserName("Ram")).thenReturn(Users.builder().userName("Ram").password("life").roles(new ArrayList<String>()).build());
		
		UserDetails userByUsername = userDetailsServiceImpl.loadUserByUsername("Ram");
		assertNotNull(userByUsername);
	}

}
