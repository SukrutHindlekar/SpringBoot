package com.example.api.Interface;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.api.entities.OTP;
import com.example.api.entities.UploadFiles;
import com.example.api.entities.Users;

public interface UserInterfaceService {
	
	Users fetchuserdetails(String emailid);
	
	List<Users> getallusers();

	Users addaccount(Users user);

	String deletestatus(long idno);

	Users fetchphone(String phoneno);
	
	void addotp(Users user, OTP otp,int otp1);
	
	void uploadtoSystem(MultipartFile file);
	
	void uploadtoDatabase(MultipartFile file);

	UploadFiles downloadfromdatabase(UploadFiles uploadfiles);
}

