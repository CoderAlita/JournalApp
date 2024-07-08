package com.example.JournalApp.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.Users;
import com.example.JournalApp.service.JournalService;
import com.example.JournalApp.service.UsersService;

@RestController
@RequestMapping("/journal")
public class JournalDbEntryController {

	@Autowired
	private JournalService journalService;
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/{username}")
	public ResponseEntity<List<JournalEntry>> getAllJournalEntryByUser(@PathVariable String username) {
		Users user = usersService.findByUserName(username);
		List<JournalEntry> all =user.getJournalEntrys();
		if(all != null && !all.isEmpty())
			return new ResponseEntity<>(all,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
		Optional<JournalEntry> journalEntry = journalService.getById(id);
		if(journalEntry.isPresent())
			return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/{username}")
	public ResponseEntity<JournalEntry> add(@PathVariable String username, @RequestBody JournalEntry journalEntry) {
		try {
			
			JournalEntry journalEntry1 =journalService.add(username, journalEntry);
			return new ResponseEntity<>(journalEntry1,HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<JournalEntry> update(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
		try {
			
			JournalEntry journalEntry1 =journalService.update(id, journalEntry);
			return new ResponseEntity<>(journalEntry1 ,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/id/{username}/{id}")
	public ResponseEntity<?> delete(@PathVariable ObjectId id, @PathVariable String username) {
		try {
			journalService.delete(id,username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
