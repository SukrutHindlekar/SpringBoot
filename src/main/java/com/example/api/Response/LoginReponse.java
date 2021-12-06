package com.example.api.Response;

import java.util.Date;

public class LoginReponse {
	
	private Date timestamp;
	
	private String msg;
	
	private String status;
	
	private String token;
	
	private String firstname;
	
	private String lastname;
	
	private String phoneno;
	
	private String emailid;

	public LoginReponse(Date timestamp, String msg, String status, String token, String firstname, String lastname,
			String phoneno, String emailid) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.status = status;
		this.token = token;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneno = phoneno;
		this.emailid = emailid;
	}

	public LoginReponse() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "LoginReponse [timestamp=" + timestamp + ", msg=" + msg + ", status=" + status + ", token=" + token
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", phoneno=" + phoneno + ", emailid="
				+ emailid + "]";
	}
	
	
	
}
