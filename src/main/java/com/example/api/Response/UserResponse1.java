package com.example.api.Response;

import java.util.Date;
import java.util.List;

import com.example.api.entities.Users;

public class UserResponse1 {
	
	private Date timestamp;
	private String message;
	private String status;
	private String user;
	public UserResponse1() {
		super();
	}
	public UserResponse1(Date timestamp, String message, String status, String user) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.user = user;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserResponse1(String message, String user) {
		super();
		this.message = message;
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "UserResponse1 [message=" + message + ", status=" + status + ", user=" + user + "]";
	}
	
}
