package com.example.payload.response;


import java.util.List;


public class JwtResponse {
	private String token;
	private String type = "Bearer";
	
	private String username;
	
	private List<String> roles;
	private boolean success;
	

	public JwtResponse(String accessToken,  String username, List<String> roles,boolean success) {
		this.token = accessToken;
	
		this.username = username;
		this.success = success;
		this.roles = roles;
		
	}
	
	
	


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	
	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
	


}