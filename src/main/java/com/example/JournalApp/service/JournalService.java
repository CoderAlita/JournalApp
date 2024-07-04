package com.example.JournalApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.repository.JournalRepository;

@Service
public class JournalService {
	
	@Autowired
	private JournalRepository journalRepository;
	
	public List<JournalEntry> getAll() {
		return journalRepository.findAll();
	}
	
	public JournalEntry getById(String id) {
		Optional<JournalEntry> journalEntry=journalRepository.findById(id);
		return journalEntry.get();
	}
	
	public JournalEntry add(JournalEntry journalEntry) {
		return journalRepository.save(journalEntry);
	}
	
	public JournalEntry update(String id,JournalEntry journalEntry) {
		JournalEntry old=journalRepository.findById(id).get();
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		
		return journalRepository.save(old);
	}
	
	public void delete(String id) {
		
		journalRepository.deleteById(id);
	}
}
