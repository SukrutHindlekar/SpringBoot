package com.example.api.Response;

import java.util.Date;

public class CustomResponse {
	
	private Date timestamp;
	private String msg;
	private	String status;
	public CustomResponse(Date timestamp, String msg, String status) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.status = status;
	}
	public CustomResponse() {
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
	@Override
	public String toString() {
		return "CustomInvalidResponse [msg=" + msg + ", status=" + status + "]";
	}
	
}
