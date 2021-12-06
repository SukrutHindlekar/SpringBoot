package com.example.api.Controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.Repository.FilesRepository;
import com.example.api.Repository.OtpRepository;
import com.example.api.Repository.UserRepository;
import com.example.api.Response.CustomResponse;
import com.example.api.Response.ExceptionResponse;
import com.example.api.Response.FileDownloadResponse;
import com.example.api.Response.LoginReponse;
import com.example.api.Response.OtpGeneratorResponse;
import com.example.api.Response.RegisterationResponse;
import com.example.api.Service.MailService;
import com.example.api.Service.UserServiceImplementation;
import com.example.api.Validation.OtpGenerate;
import com.example.api.Validation.UserValidation;
import com.example.api.entities.OTP;
import com.example.api.entities.UploadFiles;
import com.example.api.entities.Users;
import com.example.api.security.JwtDetailsimpl;
import com.example.api.security.Jwtutils;

@RestController
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	Jwtutils jwtutils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OtpRepository otpRepository;
	
	@Autowired
	UserServiceImplementation userServiceImplementation;
	
	@Autowired
	UserValidation userValidation;
	
	@Autowired
	OtpGenerate otpGenerate;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	FilesRepository filesRepository;
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/registerUser")
	public ResponseEntity<Object> registerUser(@RequestBody Users user)
	{
		userValidation.validateEmail(user);
		try {
			if(userRepository.getuserdetails(user.getEmailId())==null && userRepository.fetchphone(user.getPhoneNo())==null)
			{
				userServiceImplementation.addaccount(user);
				RegisterationResponse response = new RegisterationResponse(new Date(), "User registered", "200",user);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "UserID or Phonenumber already exists", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}		
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "User not registered", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<Object> loginUser(@RequestBody Users user) throws Exception
	{
		Users userdetails = userRepository.fetchuser(user.getEmailId());
		userValidation.validateLogin(user);
		try {
			if(userRepository.getuserdetails(userdetails.getEmailId())!=null)
			{
				if(passwordEncoder.matches(user.getPassword(), userdetails.getPassword()))
				{
					if(userdetails.getStatus()==1)
					{
						log.info("Authenticating user and generating jwt token");
						Authentication authentication = authenticationManager.authenticate(new 
								UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword()));	
						log.info("Passing user details to security contextholder");
						SecurityContextHolder.getContext().setAuthentication(authentication);
						log.info("Generating jwt token in jwtutil");
						String jwt = jwtutils.generatejwtToken(authentication);
						log.info("jwt token is "+jwt);
						JwtDetailsimpl details = (JwtDetailsimpl) authentication.getPrincipal();
						log.info("useeDetails after sign in are "+ details);						
						LoginReponse response = new LoginReponse(new Date(), "User Details are","200",jwt,userdetails.getFirstName(),
								userdetails.getLastname(),userdetails.getPhoneNo(),userdetails.getEmailId());
						return new ResponseEntity<Object>(response, HttpStatus.OK);
					}
					else
					{
					CustomResponse response = new CustomResponse(new Date(), "User is not verified", "400");
					return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
					}
				}
				else
				{
					CustomResponse response = new CustomResponse(new Date(), "Incorrect Password Entered", "400");
					return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
				}			
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "User doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}		
	}
	
	@PostMapping("/updateProfiledetails")
	public ResponseEntity<Object> updateProfiledetails(@RequestBody Users user) throws Exception
	{
		String emailid = user.getEmailId();
		userValidation.validateUpdate(user);
		try {
			if(userRepository.getuserdetails(user.getEmailId())!=null)
			{
				userServiceImplementation.updateaccount(user.getDateofBirth(), user.getFirstName(), user.getLastname(), user.getPhoneNo(), emailid);
				Users list1 = userRepository.getuserdetails(user.getEmailId());
				RegisterationResponse response = new RegisterationResponse(new Date(), "Updated User details are", "200",list1);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "EmailID doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/otpPhone")
	public ResponseEntity<Object> otpPhone(@RequestBody Users user) throws Exception 
	{
		userValidation.validateotpPhone(user);
		String phoneno = user.getPhoneNo();
		try {
			if(userServiceImplementation.fetchphone(phoneno)!=null)
			{
				OtpGeneratorResponse response = new OtpGeneratorResponse(new Date(), "Otp is", "200",otpGenerate.otpgenerate());
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "Phone number doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/otpEmail")
	public ResponseEntity<Object> otpEmail(@RequestBody Users user,OTP otp) throws Exception
	{
		String email = user.getEmailId();
		int otp1 = otpGenerate.otpgenerate();
		userValidation.validateotpEmail(user);
		try {
			if(userServiceImplementation.fetchuserdetails(email)!=null)
			{
				mailService.sendEmail(email,otp1);
				userServiceImplementation.addotp(user,otp,otp1);
				CustomResponse response = new CustomResponse(new Date(), "Email has been sent to your mail", "200");
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "EmailID doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);	
		}
	}
	
	@PostMapping("/checkOTP")
	public ResponseEntity<Object> checkOTP(@RequestBody Users user,OTP otp) throws Exception
	{
	
		long currentuserid = userRepository.fetchuser(user.getEmailId()).getId();
		userValidation.validateotpEmail(user);
		userValidation.validateuserOTP(user, otp);
		try {
			if(userServiceImplementation.fetchuserdetails(user.getEmailId())!=null)
			{
				if(user.getOtp() == userServiceImplementation.validateOTP(user,otp,currentuserid))
				{
					userServiceImplementation.setStatus(user.getEmailId());
					CustomResponse response = new CustomResponse(new Date(), "OTP Entered successfully", "200");
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				}
				else
				{
					CustomResponse response = new CustomResponse(new Date(), "Please enter valid OTP", "400");
					return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
				}			
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "EmailID doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<Object> forgotPassword(@RequestBody Users user) throws Exception
	{
		userValidation.validateLogin(user);
		try {
			if(userRepository.fetchuser(user.getEmailId())!=null)
			{
				if(passwordEncoder.matches(user.getPassword(), userRepository.fetchuser(user.getEmailId()).getPassword()))
				{
					if(userRepository.fetchuser(user.getEmailId()).getStatus()==1)
					{
						CustomResponse response = new CustomResponse(new Date(), "Enter Old password to set new password", "200");
						return new ResponseEntity<Object>(response, HttpStatus.OK);
					}
					else
					{
						CustomResponse response = new CustomResponse(new Date(), "User not verified", "400");
						return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);}}
				else
				{
					CustomResponse response = new CustomResponse(new Date(), "Incorrect password entered", "400");
					return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);}}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "EmailID doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);}} 
		catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<Object> resetPassword(@RequestBody Users user) 
	{
		userValidation.validatePassword(user);
		long idno = user.getId();
		String oldpassword = user.getPassword();
		String rawpassword = userRepository.getdeletestatus(idno).getPassword();
		try {
			if(userRepository.getdeletestatus(idno)!=null)
			{
				if(passwordEncoder.matches(oldpassword, rawpassword))
				{	
					if(user.getNewpassword().matches(oldpassword))
					{
						CustomResponse response = new CustomResponse(new Date(), "Please enter different new password", "400");
						return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
					}
					else
					{	
						userServiceImplementation.resetPass(user,idno);
						CustomResponse response = new CustomResponse(new Date(), "Password changed successfully", "200");
						return new ResponseEntity<Object>(response, HttpStatus.OK);	}}					
				else
				{
					CustomResponse response = new CustomResponse(new Date(), "Old password is incorrect or ID mismatches", "400");
					return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);}}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "ID doesnt matches password", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);}} 
			catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/uploadImagetoSystem")
	public ResponseEntity<Object> uploadImagetoSystem(@RequestParam("file")MultipartFile multipartFile)
	{
		userServiceImplementation.uploadtoSystem(multipartFile);
		CustomResponse response = new CustomResponse(new Date(), "File uploaded successfully", "200");
		return new ResponseEntity<Object>(response, HttpStatus.OK);	
	}
	
	@PostMapping("/uploadImagetoDatabase")
	public ResponseEntity<Object> uploadImagetoDatabase(@RequestParam("file")MultipartFile multipartFile)
	{
		userServiceImplementation.uploadtoDatabase(multipartFile);
		CustomResponse response = new CustomResponse(new Date(), "Image uploaded to database", "200");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	
	@PostMapping("/downloadImagefromDatabase")
	public ResponseEntity<Object> downloadImagefromDatabase(@RequestBody UploadFiles uploadfiles)
	{
		try {
			if(filesRepository.downloadfromDB(uploadfiles.getFileId())!= null)
			{
				userServiceImplementation.downloadfromdatabase(uploadfiles);
				UploadFiles uploadfile = filesRepository.downloadfromDB(uploadfiles.getFileId());
				FileDownloadResponse response = new FileDownloadResponse(new Date(), "Image url accessed from database", "200",uploadfile);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			else
			{
				CustomResponse response = new CustomResponse(new Date(), "ID doesnt exists in records", "400");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			ExceptionResponse response = new ExceptionResponse(new Date(), "Exception occured", "500", exception);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}	
	}		
	
}