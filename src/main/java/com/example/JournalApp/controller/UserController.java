package com.example.JournalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JournalApp.entity.Users;
import com.example.JournalApp.service.UsersService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/get")
	public List<Users> getAll() {
		List<Users> users = usersService.getAll();
		return  users;
	}
	@GetMapping
	public ResponseEntity<Users> findByName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = usersService.findByUserName(authentication.getName());
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Users>  update(@RequestBody Users user) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users old = usersService.findByUserName(username);
		
		if(old !=null) {
			old.setUserName(user.getUserName());
			old.setPassword(user.getPassword());
		}
		Users user1 = usersService.saveUsers(old);
		return new ResponseEntity<Users>(user1, HttpStatus.OK);
		}
	
	@DeleteMapping
	public ResponseEntity<?> delete() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		usersService.deleteByUserName(authentication.getName());
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}

}
