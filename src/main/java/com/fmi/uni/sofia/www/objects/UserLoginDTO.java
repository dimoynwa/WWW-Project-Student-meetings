package com.fmi.uni.sofia.www.objects;

public class UserLoginDTO {
	
	private String username;
	private String password;
	
	public UserLoginDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
