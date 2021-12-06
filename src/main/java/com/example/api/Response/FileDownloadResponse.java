package com.example.api.Response;

import java.util.Date;

import com.example.api.entities.UploadFiles;

public class FileDownloadResponse {
	
	private Date timestamp;
	private String msg;
	private String status;
	private UploadFiles files;
	public FileDownloadResponse(Date timestamp, String msg, String status, UploadFiles files) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.status = status;
		this.files = files;
	}
	public FileDownloadResponse() {
		super();
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
	public UploadFiles getFiles() {
		return files;
	}
	public void setFiles(UploadFiles files) {
		this.files = files;
	}
	@Override
	public String toString() {
		return "FileDownloadResponse [timestamp=" + timestamp + ", msg=" + msg + ", status=" + status + ", files="
				+ files + "]";
	}
	
}
