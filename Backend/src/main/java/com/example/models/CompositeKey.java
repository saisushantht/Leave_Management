package com.example.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable{
	
	
	
	private String username;
	
	private String dt;

	public CompositeKey() {
		super();
	}

	
}
