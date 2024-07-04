package com.example.JournalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping("/journalc")
public class JournalEntryController {
	
	private Map<String,JournalEntry> entries = new HashMap<>();

	@GetMapping("/get")
	public List<JournalEntry> getAll() {
		return new ArrayList<JournalEntry>(entries.values());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> getById(@PathVariable String id){
		JournalEntry journalEntry =entries.get(id);
		return ResponseEntity.ok(journalEntry);
	}
	
	@PostMapping("/add")
	public JournalEntry add(@RequestBody JournalEntry journalEntry ) {
		entries.put(journalEntry.getId(), journalEntry);
		return journalEntry;
	}
	
	@PutMapping("/id/{id}")
	public JournalEntry update(@PathVariable String id, @RequestBody JournalEntry journalEntry) {
		JournalEntry old = entries.get(id);
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		return old;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@DeleteMapping("/id/{id}")
	public JournalEntry removeById(@PathVariable String id) {
		return entries.remove(id);
	}
	
	@DeleteMapping("/delete")
	public void remove( ) {
		entries.clear();
	}
}
