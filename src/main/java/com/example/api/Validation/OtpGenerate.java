package com.example.api.Validation;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpGenerate {
	
	public int otpgenerate()
	{
		Random ran = new Random();
		int n = 1000 + ran.nextInt(8999);
		return n;
	}
}
