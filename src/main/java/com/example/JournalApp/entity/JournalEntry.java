package com.example.JournalApp.entity;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "JournalEntry")
@Data
public class JournalEntry {
	@Id
	private ObjectId id;
	private String title;
	private String content;
	private LocalDate date;
	
}
