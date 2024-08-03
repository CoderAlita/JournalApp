package com.example.JournalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.JournalApp.api.response.CatResponse;

@Service
public class CatService {

	public static final String key ="live_qhtqhdrq9ms6eEWSb5qrz0owilMT3KAw1NJPGbKFYdEJ6htLGIYp8zjbOV7KSKHf";
	public static final String API = "https://api.thecatapi.com/v1/images/search?limit=10&breed_ids=beng&api_key=API_KEY";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public CatResponse getCatBreed() {
		
		String finalApi = API.replace("API_KEY", key);
		ResponseEntity<CatResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET,null,CatResponse.class);
		CatResponse body = response.getBody();
		return body;
	}
	
	
}
