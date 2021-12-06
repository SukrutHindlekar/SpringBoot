package com.example.api.Response;

import java.util.Date;
import java.util.List;

import com.example.api.entities.Users;

public class AccessdataResponse {
	
	private Date timestamp;
	private String message;
	private String status;
	private List<Users> list;
	public AccessdataResponse() {
		super();	
	}
	@Override
	public String toString() {
		return "AccessdataResponse [message=" + message + ", status=" + status + ", list=" + list + "]";
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
	public AccessdataResponse(Date timestamp, String message, String status, List<Users> list) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.list = list;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Users> getList() {
		return list;
	}
	public void setList(List<Users> list) {
		this.list = list;
	}
	public AccessdataResponse(String message, List<Users> list) {
		super();
		this.message = message;
		this.list = list;
	}
	
}
