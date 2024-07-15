package com.example.JournalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.service.UsersService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/get")
	public ResponseEntity<?> getAll() {
		List<Users> users = usersService.getAll();
		if(users!= null && !users.isEmpty())
			return  new ResponseEntity<>(users,HttpStatus.OK);
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Users>  addAdmin(@RequestBody Users users) {
		Users users1 = usersService.saveAdmin(users);
		return new ResponseEntity<Users>(users1, HttpStatus.CREATED);
	}

}
