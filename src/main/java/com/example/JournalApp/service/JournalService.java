package com.example.JournalApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.Users;
import com.example.JournalApp.repository.JournalRepository;

@Service
public class JournalService {
	
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	UsersService usersService;
	public List<JournalEntry> getAll() {
		return journalRepository.findAll();
	}
	
	public Optional<JournalEntry> getById(ObjectId id) {
		return journalRepository.findById(id);
	}
	
	public JournalEntry add(String username, JournalEntry journalEntry) {
		Users byUserName = usersService.findByUserName(username);
		journalEntry.setDate(LocalDate.now());
		JournalEntry save = journalRepository.save(journalEntry);
		byUserName.getJournalEntrys().add(save);
		usersService.saveUsers(byUserName);
		return save;
	}
	
	public JournalEntry update(ObjectId id,JournalEntry journalEntry) {
		
		JournalEntry old=journalRepository.findById(id).get();
		old.setTitle(journalEntry.getTitle());
		old.setContent(journalEntry.getContent());
		
		return journalRepository.save(old);
	}
	
	public void delete(ObjectId id, String username) {
		Users user = usersService.findByUserName(username);
		user.getJournalEntrys().removeIf(x-> x.getId().equals(id));
		usersService.saveUsers(user);
		journalRepository.deleteById(id);
	}
}
