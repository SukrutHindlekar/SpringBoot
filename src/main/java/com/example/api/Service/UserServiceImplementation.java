package com.example.api.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.Interface.UserInterfaceService;
import com.example.api.Repository.FilesRepository;
import com.example.api.Repository.OtpRepository;
import com.example.api.Repository.UserRepository;
import com.example.api.Validation.OtpGenerate;
import com.example.api.entities.OTP;
import com.example.api.entities.UploadFiles;
import com.example.api.entities.Users;


@Service
public class UserServiceImplementation implements UserInterfaceService{
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder	passwordEncoder;
	
	@Autowired
	OtpGenerate otpGenerate;

	@Autowired
	OtpRepository otpRepository;
	
	@Autowired
	FilesRepository filesRepository;
	
	@Override
	public Users addaccount(Users user) {
		String encodedpassword = passwordEncoder.encode(user.getPassword());
		int n = 6;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
 
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }   
        user.setReferalCode(sb.toString());
        user.setDate(java.time.LocalDate.now());
        user.setPassword(encodedpassword);
		return userRepository.save(user);
	}

	@Override
	public List<Users> getallusers() {
		return userRepository.getdetails();	
	}
	
	@Override
	public Users fetchuserdetails(String emailid) {
		
		return userRepository.fetchuser(emailid);
	}
	
	@Override
	public Users fetchphone(String phoneno)
	{
		return userRepository.fetchphone(phoneno);
	}

	public int updateaccount(String dob, String firstname, String lastname, String phoneno, String emailid) {
		
		return userRepository.updatedetails(dob, firstname, lastname, phoneno, emailid);	
	}

	public int setStatus(String emailid)
	{
		return userRepository.setstatus(emailid);
	}

	public String deletestatus(long idno) {
		
		return userRepository.getdeletestatus(idno).getEmailId();
	}
	
	public Users otpPhone(String number)
	{
		return userRepository.getotpnumber(number);
	}

	public void addotp(Users user, OTP otp,int otp1) {
		
		long currentuserid = userRepository.fetchuser(user.getEmailId()).getId();
        if(otpRepository.fetchotpdetails(currentuserid)== null)
        {
        	otp.setId(userRepository.getuserdetails(user.getEmailId()).getId());
        	otp.setTempotp(otp1);
        	otpRepository.save(otp);
        }
        else
        {
        	OTP otp2 = otpRepository.fetchotpdetails(currentuserid);
        	long idno = otp2.getIdno();
        	otpRepository.updateotp(otp1,idno);
        }     
	}
	
	public int validateOTP(Users user,OTP otp, long currentuserid) {
		return otpRepository.fetchotpdetails(currentuserid).getTempotp();
	}
	
	public void resetPass(Users user,long idno)
	{
		String encodedpassword = passwordEncoder.encode(user.getNewpassword());
		userRepository.updatePass(encodedpassword, idno);
	}

	
	private String uploadfolderpath = "C:\\Users\\91955\\Desktop\\RPA\\"+System.currentTimeMillis();
	@Override
	public void uploadtoSystem(MultipartFile file) {
		
		String fileExtension = file.getOriginalFilename().split("\\.")[1];
		try {
			byte[] data = file.getBytes();
			Path path = Paths.get(uploadfolderpath+"."+fileExtension);
			System.out.println(path);
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void uploadtoDatabase(MultipartFile file) {
		
		String fileExtension = file.getOriginalFilename().split("\\.")[1];
		UploadFiles files = new UploadFiles();
		String fileName = System.currentTimeMillis()+"."+fileExtension;
		try {
			files.setFileData(file.getBytes());
			files.setFiletype(file.getContentType());
			files.setFileName(fileName);
			filesRepository.save(files);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UploadFiles downloadfromdatabase(UploadFiles uploadfiles) {
		
		return filesRepository.downloadfromDB(uploadfiles.getFileId());
	}

//	public UploadFiles downloadfile(String fileId) {
//
//		UploadFiles uploadFiles = filesRepository.getOne(fileId);
//		return uploadFiles;
//	}	
	
}
