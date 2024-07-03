package com.example.JournalApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.JournalApp.entity.JournalEntry;

@Repository
public interface JournalRepository extends MongoRepository<JournalEntry,Long>{

}
