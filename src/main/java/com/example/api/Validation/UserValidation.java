package com.example.api.Validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.api.entities.OTP;
import com.example.api.entities.Users;
import com.example.api.exception.InvalidRequestException;

@Service
public class UserValidation {
	
	
	Logger log = LoggerFactory.getLogger(UserValidation.class);
	
	public void validateEmail(Users user) 
	{
		String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String passwordRegex = "^.*(?=.{8,15})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		String phoneRegex = "[a-zA-Z&._-]";
		String DOBRegex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
		if(user.getEmailId().equals(""))			
		{log.info("Email cannot be empty"); throw new InvalidRequestException("Email cannot be empty"); }
		
		if(!(user.getEmailId().matches(emailRegex)))
		{
		log.info("Please enter valid email ID");throw new InvalidRequestException("Please enter valid email ID");
		}
		
		if(user.getPassword().equals(""))				
		{log.info("Password cannot be empty");throw new InvalidRequestException ("Password cannot be empty");}
		
		if(!(user.getPassword().matches(passwordRegex)))
		{log.info("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");throw new InvalidRequestException("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");
		}
		
		if(user.getPhoneNo().equals(null))			
		{log.info("Phone number cannot be empty");throw new InvalidRequestException ("Phone number cannot be empty");
		}
		
		if(user.getPhoneNo().matches(phoneRegex))
		{log.info("Phone number cannot contains alphabets or special letters");throw new InvalidRequestException("Phone number cannot contains alphabets or special letters");}		
		
		if(!(user.getPhoneNo().length() == 10 && (user.getPhoneNo().startsWith("7")||user.getPhoneNo().startsWith("8")||user.getPhoneNo().startsWith("9"))))
		{log.info("Please enter valid phone number starting from 7,8 or 9");throw new InvalidRequestException("Please enter valid phone number starting from 7,8 or 9");
		}
		
		if(user.getFirstName()=="") 
		{log.info("First name cannot be empty");throw new InvalidRequestException("First name cannot be empty");}
		
		if(user.getFirstName().length()<2 || user.getFirstName().length()>30)
		{log.info("First name should not be less than 2 alphabets or more than 30 alphabets");throw new InvalidRequestException("First name should not be less than 2 alphabets or more than 30 alphabets");}
		
		if(!(user.getFirstName().matches("[a-zA-Z]+") == true || user.getFirstName().matches("[A-Za-z]+") == true
				|| user.getFirstName().matches("[a-z]+") == true ||user.getFirstName().matches("[A-Z]+") == true))
		{log.info("First name should contain only alphabets");throw new InvalidRequestException("First name should contain only alphabets");
		}
		
		if(user.getLastname()=="") 
		{log.info("Last name cannot be empty");throw new InvalidRequestException("Last name cannot be empty");}
		
		if(user.getLastname() == user.getFirstName()) 
		{log.info("Last name same as firstname");throw new InvalidRequestException("Last name same as firstname");}
		
		if(user.getLastname().length()<2 || user.getLastname().length()>30)
		{log.info("Last name should not be less than 2 alphabets or more than 30 alphabets");throw new InvalidRequestException("Last name should not be less than 2 alphabets or more than 30 alphabets");}
		
		if(!(user.getLastname().matches("[a-zA-Z]+") == true || user.getLastname().matches("[A-Za-z]+") == true
				|| user.getLastname().matches("[a-z]+") == true ||user.getLastname().matches("[A-Z]+") == true)) 
		{log.info("Last name should contain only alphabets");throw new InvalidRequestException("Last name should contain only alphabets");
		}
		
		if(user.getDateofBirth()=="")
		{log.info("Date of birth cannot be null");throw new InvalidRequestException("Date of birth cannot be null");}
		
		if(!(user.getDateofBirth().matches(DOBRegex)))
		{log.info("Please enter valid date of birth");throw new InvalidRequestException("Please enter valid date of birth");}

	}
	public void validateLogin(Users user) throws Exception
	{
		String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String passwordRegex = "^.*(?=.{8,15})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		if(user.getEmailId().equals(""))			
		{log.info("Email cannot be empty"); throw new InvalidRequestException("Email cannot be empty"); }
		
		if(!(user.getEmailId().matches(emailRegex)))
		{
		log.info("Please enter valid email ID");throw new InvalidRequestException("Please enter valid email ID");
		}
		
		if(user.getPassword().equals(""))				
		{log.info("Password cannot be empty");throw new InvalidRequestException ("Password cannot be empty");}
		
		if(!(user.getPassword().matches(passwordRegex)))
		{log.info("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");throw new InvalidRequestException("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");
		}	
	}	
		public void validateUpdate(Users user) throws Exception
	{
			String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			String phoneRegex = "[a-zA-Z&._-]";
			String DOBRegex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
			if(user.getEmailId().equals(""))			
			{log.info("Email cannot be empty"); throw new InvalidRequestException("Email cannot be empty"); }
			
			if(!(user.getEmailId().matches(emailRegex)))
			{
			log.info("Please enter valid email ID");throw new InvalidRequestException("Please enter valid email ID");
			}	
			if(user.getPhoneNo().equals(null))			
			{log.info("Phone number cannot be empty");throw new InvalidRequestException ("Phone number cannot be empty");
			}
			
			if(user.getPhoneNo().matches(phoneRegex))
			{log.info("Phone number cannot contains alphabets or special letters");throw new InvalidRequestException("Phone number cannot contains alphabets or special letters");}		
			
			if(!(user.getPhoneNo().length() == 10 && (user.getPhoneNo().startsWith("7")||user.getPhoneNo().startsWith("8")||user.getPhoneNo().startsWith("9"))))
			{log.info("Please enter valid phone number starting from 7,8 or 9");throw new InvalidRequestException("Please enter valid phone number starting from 7,8 or 9");
			}
			
			if(user.getFirstName()=="") 
			{log.info("First name cannot be empty");throw new InvalidRequestException("First name cannot be empty");}
			
			if(user.getFirstName().length()<2 || user.getFirstName().length()>30)
			{log.info("First name should not be less than 2 alphabets or more than 30 alphabets");throw new InvalidRequestException("First name should not be less than 2 alphabets or more than 30 alphabets");}
			
			if(!(user.getFirstName().matches("[a-zA-Z]+") == true || user.getFirstName().matches("[A-Za-z]+") == true
					|| user.getFirstName().matches("[a-z]+") == true ||user.getFirstName().matches("[A-Z]+") == true))
			{log.info("First name should contain only alphabets");throw new InvalidRequestException("First name should contain only alphabets");
			}
			
			if(user.getLastname()=="") 
			{log.info("Last name cannot be empty");throw new InvalidRequestException("Last name cannot be empty");}
			
			if(user.getLastname() == user.getFirstName()) 
			{log.info("Last name same as firstname");throw new InvalidRequestException("Last name same as firstname");}
			
			if(user.getLastname().length()<2 || user.getLastname().length()>30)
			{log.info("Last name should not be less than 2 alphabets or more than 30 alphabets");throw new InvalidRequestException("Last name should not be less than 2 alphabets or more than 30 alphabets");}
			
			if(!(user.getLastname().matches("[a-zA-Z]+") == true || user.getLastname().matches("[A-Za-z]+") == true
					|| user.getLastname().matches("[a-z]+") == true ||user.getLastname().matches("[A-Z]+") == true)) 
			{log.info("Last name should contain only alphabets");throw new InvalidRequestException("Last name should contain only alphabets");
			}
			
			if(user.getDateofBirth()=="")
			{log.info("Date of birth cannot be null");throw new InvalidRequestException("Date of birth cannot be null");}
			
			if(!(user.getDateofBirth().matches(DOBRegex)))
			{log.info("Please enter date of birth in DD-MM-YYYY format");throw new InvalidRequestException("Please enter date of birth in DD-MM-YYYY format");}
	}
		public void validateotpPhone(Users user) throws Exception
		{
			String phoneRegex = "[a-zA-Z&._-]";
			if(user.getPhoneNo().equals(null))			
			{log.info("Phone number cannot be empty");throw new InvalidRequestException ("Phone number cannot be empty");
			}
			
			if(user.getPhoneNo().matches(phoneRegex))
			{log.info("Phone number cannot contains alphabets or special letters");throw new InvalidRequestException("Phone number cannot contains alphabets or special letters");}		
			
			if(!(user.getPhoneNo().length() == 10 && (user.getPhoneNo().startsWith("7")||user.getPhoneNo().startsWith("8")||user.getPhoneNo().startsWith("9"))))
			{log.info("Please enter valid phone number starting from 7,8 or 9");throw new InvalidRequestException("Please enter valid phone number starting from 7,8 or 9");
			}
		}
		
		public void validateotpEmail (Users user) throws Exception
		{
			String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			if(user.getEmailId().equals(""))			
			{log.info("Email cannot be empty"); throw new InvalidRequestException("Email cannot be empty"); }
			
			if(!(user.getEmailId().matches(emailRegex)))
			{
			log.info("Please enter valid email ID");throw new InvalidRequestException("Please enter valid email ID");
			}
		}
		
		public void validateuserOTP( Users user, OTP otp) throws Exception
		{
			if(user.getOtp()<4 && user.getOtp()>4)
			{log.info("Please enter 4 digit otp"); throw new InvalidRequestException("Please enter 4 digit otp"); }			
		}
		
		public void validatePassword( Users user)
		{
			String passwordRegex = "^.*(?=.{8,15})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
			if(user.getPassword().equals(""))				
			{log.info("Password cannot be empty");throw new InvalidRequestException ("Password cannot be empty");}
			
			if(!(user.getPassword().matches(passwordRegex)))
			{log.info("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");throw new InvalidRequestException("Please enter valid password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");
			}	
			if(user.getNewpassword().equals(""))				
			{log.info("Password cannot be empty");throw new InvalidRequestException ("Password cannot be empty");}
			
			if(!(user.getNewpassword().matches(passwordRegex)))
			{log.info("Please enter valid new password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");throw new InvalidRequestException("Please enter valid new password containing 1 Uppercase letter, 1 Lowercase letter, 1 special charachter and 1 number and between 8 to 15 charachters only");
			}
		}
}
