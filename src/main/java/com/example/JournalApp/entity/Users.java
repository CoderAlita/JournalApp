package com.example.JournalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
@Data
@Document(collection = "Users")
public class Users {
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@DBRef
	private List<JournalEntry> journalEntrys = new ArrayList<>();
	
}
