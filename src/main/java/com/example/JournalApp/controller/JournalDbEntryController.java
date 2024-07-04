package com.example.JournalApp.controller;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<JournalEntry> getAll() {
		return journalService.getAll();
	}
	@GetMapping("/id/{id}")
	public JournalEntry getById(@PathVariable ObjectId id) {
		return journalService.getById(id).orElse(null);
	}
	@PostMapping("/add")
	public JournalEntry add(@RequestBody JournalEntry journalEntry) {
		journalEntry.setDate(LocalDate.now());
		return journalService.add(journalEntry);
	}
	@PutMapping("/id/{id}")
	public JournalEntry update(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
		return journalService.update(id, journalEntry);
	}
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable ObjectId id) {
		journalService.delete(id);
	}
}
