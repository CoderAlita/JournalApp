package com.example.JournalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/{userName}")
	public ResponseEntity<Users> findByName(@PathVariable String userName) {
		Users user = usersService.findByUserName(userName);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Users>  add(@RequestBody Users users) {
		Users users1 = usersService.saveUsers(users);
		return new ResponseEntity<Users>(users1, HttpStatus.CREATED);
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<Users>  update(@PathVariable String username,@RequestBody Users user) {
		Users old = usersService.findByUserName(username);
		if(old !=null) {
			old.setUserName(user.getUserName());
			old.setPassword(user.getPassword());
		}
		Users user1 = usersService.saveUsers(old);
		return new ResponseEntity<Users>(user1, HttpStatus.OK);
		}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable ObjectId id) {
		usersService.delete(id);
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	}

}
