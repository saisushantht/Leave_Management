package com.example.payload.response;

public class ReturnRequest {
	private String error;
	

	public ReturnRequest(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
