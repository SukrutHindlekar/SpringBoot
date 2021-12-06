package com.example.api.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OTP {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idno;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "id")
	private long id;
	private int tempotp;
	public OTP(long idno, long id, int tempotp) {
		super();
		this.idno = idno;
		this.id = id;
		this.tempotp = tempotp;
	}
	public OTP() {
		super();
	}
	public long getIdno() {
		return idno;
	}
	public void setIdno(long idno) {
		this.idno = idno;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTempotp() {
		return tempotp;
	}
	public void setTempotp(int tempotp) {
		this.tempotp = tempotp;
	}
	public void setOtp(int tempotp) {
		this.tempotp = tempotp;
	}
	@Override
	public String toString() {
		return "OTP [idno=" + idno + ", id=" + id + ", tempotp=" + tempotp + "]";
	}

	
	
}
