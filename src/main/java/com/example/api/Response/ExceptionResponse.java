package com.example.api.Response;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String status;
	private Exception e;
	public ExceptionResponse(Date timestamp, String message, String status, Exception e) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.e = e;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
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
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", message=" + message + ", status=" + status + ", e=" + e
				+ "]";
	}
	
}
