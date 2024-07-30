package com.example.JournalApp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
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
	@Transactional
	public void delete(ObjectId id, String username) {
		try {
			Users user = usersService.findByUserName(username);
			boolean removed = user.getJournalEntrys().removeIf(x-> x.getId().equals(id));
			if(removed) {
			usersService.saveUsers(user);
			journalRepository.deleteById(id);
			}
		} catch (Exception e) {
			throw new RuntimeException("An error has occured while deleting the journal entry",e);
		}
		
	}
}
