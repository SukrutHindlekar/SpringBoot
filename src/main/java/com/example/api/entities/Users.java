package com.example.api.entities;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate date;
	private String emailId;
	private String password;
	private String firstName;
	private String lastname;
	private String phoneNo;
	private String dateofBirth;
	private long Status = 0;
	private String referalCode;
	private long delStatus = 0;
	private int otp;
	private String newpassword;
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public Users() {
		super();
	}
	public Users(long id, LocalDate date, String emailId, String password, String firstName, String lastname,
			String phoneNo, String dateofBirth, long status, String referalCode, long delStatus, int otp,
			String newpassword) {
		super();
		this.id = id;
		this.date = date;
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastname = lastname;
		this.phoneNo = phoneNo;
		this.dateofBirth = dateofBirth;
		Status = status;
		this.referalCode = referalCode;
		this.delStatus = delStatus;
		this.otp = otp;
		this.newpassword = newpassword;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	public Users(String firstName, String lastname) {
		super();
		this.firstName = firstName;
		this.lastname = lastname;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public long getStatus() {
		return Status;
	}
	public void setStatus(long status) {
		Status = status;
	}
	public String getReferalCode() {
		return referalCode;
	}
	public void setReferalCode(String referalCode) {
		this.referalCode = referalCode;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", date=" + date + ", emailId=" + emailId + ", password=" + password + ", firstName="
				+ firstName + ", lastname=" + lastname + ", phoneNo=" + phoneNo + ", dateofBirth=" + dateofBirth
				+ ", Status=" + Status + ", referalCode=" + referalCode + ", delStatus=" + delStatus + ", otp=" + otp
				+ ", newpassword=" + newpassword + "]";
	}
	public LocalDate date() {
		return date;
	}
	public void setCurrenttime(LocalDate date) {
		this.date = date;
	}
	public long getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(long delStatus) {
		this.delStatus = delStatus;
	}
	
}
