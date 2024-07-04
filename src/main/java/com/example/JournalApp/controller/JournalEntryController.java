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
import com.example.JournalApp.entity.JournalEntryC;

@RestController
@RequestMapping("/journalc")
public class JournalEntryController {
	
	private Map<String,JournalEntryC> entries = new HashMap<>();

	@GetMapping("/get")
	public List<JournalEntryC> getAll() {
		return new ArrayList<JournalEntryC>(entries.values());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntryC> getById(@PathVariable String id){
		JournalEntryC journalEntry =entries.get(id);
		return ResponseEntity.ok(journalEntry);
	}
	
	@PostMapping("/add")
	public JournalEntryC add(@RequestBody JournalEntryC journalEntry ) {
		entries.put(journalEntry.getId(), journalEntry);
		return journalEntry;
	}
	
	@PutMapping("/id/{id}")
	public JournalEntryC update(@PathVariable String id, @RequestBody JournalEntryC journalEntry) {
		JournalEntryC old = entries.get(id);
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		return old;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@DeleteMapping("/id/{id}")
	public JournalEntryC removeById(@PathVariable String id) {
		return entries.remove(id);
	}
	
	@DeleteMapping("/delete")
	public void remove( ) {
		entries.clear();
	}
}
