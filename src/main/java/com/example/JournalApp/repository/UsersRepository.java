package com.example.JournalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.JournalApp.entity.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users,ObjectId>{
	
	Users findByUserName(String userName);
	void deleteByUserName(String username);

}
