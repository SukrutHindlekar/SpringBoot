package com.example.api.Response;

import java.util.Date;

public class OtpGeneratorResponse {
	
	private Date timestamp;
	private String status; 
	private String msg;
	private int n;
	public OtpGeneratorResponse(String msg, int n) {
		super();
		this.msg = msg;
		this.n = n;
	}
	public OtpGeneratorResponse() {
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
	public OtpGeneratorResponse(Date timestamp, String status, String msg, int n) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.msg = msg;
		this.n = n;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	@Override
	public String toString() {
		return "OtpGeneratorResponse [status=" + status + ", msg=" + msg + ", n=" + n + "]";
	}
	
}
