package com.example.JournalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.service.UsersService;

@RestController
@RequestMapping("/public")
public class PublicController {


	@Autowired
	UsersService usersService;
	
	@GetMapping("/health")
	public String health() {
		return "ok";
	}
	
	@PostMapping
	public ResponseEntity<Users>  add(@RequestBody Users users) {
		Users users1 = usersService.saveUsers(users);
		return new ResponseEntity<Users>(users1, HttpStatus.CREATED);
	}

}
