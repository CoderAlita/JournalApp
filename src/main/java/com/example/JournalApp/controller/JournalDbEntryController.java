package com.example.JournalApp.controller;

import java.util.List;

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
	@GetMapping("/get/{id}")
	public JournalEntry getById(@PathVariable String id) {
		return journalService.getById(id);
	}
	@PostMapping("/add")
	public JournalEntry add(@RequestBody JournalEntry journalEntry) {
		return journalService.add(journalEntry);
	}
	@PutMapping("/update/{id}")
	public JournalEntry update(@PathVariable String id, @RequestBody JournalEntry journalEntry) {
		return journalService.update(id, journalEntry);
	}
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable String id) {
		journalService.delete(id);
	}
}
