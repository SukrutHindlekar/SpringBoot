package com.example.api.Response;

import java.util.Date;

import com.example.api.entities.Users;

public class RegisterationResponse {
	
	private Date timestamp;
	private String msg;
	private String status;
	private Users fetchdetails;
	public RegisterationResponse() {
		super();
	}
	public RegisterationResponse(String msg, Users fetchdetails) {
		super();
		this.msg = msg;
		this.fetchdetails = fetchdetails;
	}
	public RegisterationResponse(Date date, String msg, String status, Users fetchdetails) {
		super();
		this.timestamp = date;
		this.msg = msg;
		this.status = status;
		this.fetchdetails = fetchdetails;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Users getFetchdetails() {
		return fetchdetails;
	}
	public void setFetchdetails(Users fetchdetails) {
		this.fetchdetails = fetchdetails;
	}
	@Override
	public String toString() {
		return "RegisterationResponse [timestamp=" + timestamp + ", msg=" + msg + ", status=" + status
				+ ", fetchdetails=" + fetchdetails + "]";
	}
	
	
}
