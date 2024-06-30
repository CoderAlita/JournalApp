package com.example.JournalApp.healthcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JournalApp.entity.JournalEntry;

@RestController("/journal")
public class JournalEntryController {
	
	private Map<Long,JournalEntry> entries = new HashMap<>();

	@GetMapping("/get")
	public List<JournalEntry> getAll() {
		return new ArrayList<JournalEntry>(entries.values());
	}
	
	@GetMapping("/id/{id}")
	public JournalEntry getById(@PathVariable long id){
		JournalEntry journalEntry =entries.get(id);
		return journalEntry;
	}
	
	@PostMapping("/add")
	public JournalEntry add(@RequestBody JournalEntry journalEntry ) {
		entries.put(journalEntry.getId(), journalEntry);
		return journalEntry;
	}
	
	@PutMapping("/update/{id}")
	public JournalEntry update(@PathVariable long id, @RequestBody JournalEntry journalEntry) {
		JournalEntry old = entries.get(id);
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		return old;
	}
	
	@DeleteMapping("/delete/{id}")
	public void removeById(@PathVariable int id) {
		entries.remove(id);
	}
	
	@DeleteMapping("/delete")
	public void remove( ) {
		entries.clear();
	}
}
