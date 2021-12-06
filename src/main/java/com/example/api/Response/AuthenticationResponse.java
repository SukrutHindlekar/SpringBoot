package com.example.api.Response;

import java.util.Date;

public class AuthenticationResponse {
	
	private Date timestamp;
	private String msg;
	private String status;
	private String jwt;
	public AuthenticationResponse(Date timestamp, String msg, String status, String jwt) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.status = status;
		this.jwt = jwt;
	}
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AuthenticationResponse [timestamp=" + timestamp + ", msg=" + msg + ", status=" + status + ", jwt=" + jwt
				+ "]";
	}
	
	
}
