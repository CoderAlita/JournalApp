package com.example.JournalApp.controller;

import java.time.LocalDate;
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
import com.example.JournalApp.service.JournalService;

@RestController
@RequestMapping("/journal")
public class JournalDbEntryController {

	@Autowired
	private JournalService journalService;
	@GetMapping("/get")
	public ResponseEntity<List<JournalEntry>>  getAll() {
		List<JournalEntry> all =journalService.getAll();
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
	@PostMapping("/add")
	public ResponseEntity<JournalEntry> add(@RequestBody JournalEntry journalEntry) {
		try {
			journalEntry.setDate(LocalDate.now());
			JournalEntry journalEntry1 =journalService.add(journalEntry);
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
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete(@PathVariable ObjectId id) {
		try {
			journalService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
