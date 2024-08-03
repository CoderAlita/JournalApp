package com.example.JournalApp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatResponse {
	
	// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
	// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
	/* ObjectMapper om = new ObjectMapper();
	Root[] root = om.readValue(myJsonString, Root[].class); */
	
	private Breed breed;
	
	@Getter
	@Setter
	public class Breed{

	    public String id;
	    public String name;

	}


}
