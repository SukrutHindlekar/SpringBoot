package com.example.api.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UploadFiles {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy = "uuid2")
	private String fileId;
	private String fileName;
	private String filetype;
	@Lob
	private byte[] fileData;
	public UploadFiles(String fileId, String fileName, String filetype, byte[] fileData) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.filetype = filetype;
		this.fileData = fileData;
	}
	public UploadFiles() {
		super();
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	@Override
	public String toString() {
		return "Files [fileId=" + fileId + ", fileName=" + fileName + ", filetype=" + filetype + ", fileData="
				+ Arrays.toString(fileData) + "]";
	}
	
}
