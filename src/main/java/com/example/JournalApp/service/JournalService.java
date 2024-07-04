package com.example.JournalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
	
	public Optional<JournalEntry> getById(ObjectId id) {
		return journalRepository.findById(id);
	}
	
	public JournalEntry add(JournalEntry journalEntry) {
		return journalRepository.save(journalEntry);
	}
	
	public JournalEntry update(ObjectId id,JournalEntry journalEntry) {
		JournalEntry old=journalRepository.findById(id).get();
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		
		return journalRepository.save(old);
	}
	
	public void delete(ObjectId id) {
		
		journalRepository.deleteById(id);
	}
}
