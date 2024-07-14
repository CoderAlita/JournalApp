package com.example.JournalApp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@GetMapping
	public ResponseEntity<List<JournalEntry>> getAllJournalEntryByUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Users user = usersService.findByUserName(username);
		List<JournalEntry> all =user.getJournalEntrys();
		if(all != null && !all.isEmpty())
			return new ResponseEntity<>(all,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = usersService.findByUserName(authentication.getName());
		List<JournalEntry> collect = user.getJournalEntrys().stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry> journalEntry = journalService.getById(id);
			if(journalEntry.isPresent())
				return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<JournalEntry> add( @RequestBody JournalEntry journalEntry) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			JournalEntry journalEntry1 =journalService.add(username, journalEntry);
			return new ResponseEntity<>(journalEntry1,HttpStatus.CREATED);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<JournalEntry> update(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry) {
		try {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = usersService.findByUserName(authentication.getName());
			List<JournalEntry> collect = user.getJournalEntrys().stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());
			if(!collect.isEmpty()) {
			
					JournalEntry journalEntry1 =journalService.update(id, journalEntry);
					return new ResponseEntity<>(journalEntry1 ,HttpStatus.OK);
				}			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete(@PathVariable ObjectId id) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			journalService.delete(id,username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
