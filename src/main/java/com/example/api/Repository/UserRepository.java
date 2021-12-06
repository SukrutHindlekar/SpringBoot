package com.example.api.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	@Query("SELECT t FROM Users t where t.emailId=:emailid")
	Users fetchuser(String emailid);
	
	@Query("SELECT t FROM Users t where t.phoneNo=:phoneno")
	Users fetchphone(String phoneno);
	
	@Query("SELECT t FROM Users t")
	List<Users> getdetails();

	@Query("SELECT t FROM Users t where t.emailId=:email")
	Users getuserdetails(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE Users t SET t.dateofBirth=:dob , t.firstName=:firstname, t.lastname=:lastname, t.phoneNo=:phoneno where t.emailId=:emailid" )
	int updatedetails(String dob, String firstname, String lastname, String phoneno, String emailid);
	
	@Modifying
	@Transactional
	@Query("UPDATE Users t set t.delStatus='1' where t.id=:idno")
	int updatestatus(long idno);
	
	@Modifying
	@Transactional
	@Query("UPDATE Users t set t.Status='1' where t.emailId=:emailid")
	int setstatus(String emailid);
	
	@Query("SELECT t FROM Users t where t.id=:idno")
	Users getdeletestatus(long idno);
	
	@Query("SELECT t FROM Users t where t.phoneNo=:number")
	Users getotpnumber(String number);
	
	@Modifying
	@Transactional
	@Query("UPDATE Users t SET t.password=:Newpassword where t.id=:idno")
	int updatePass(String Newpassword, long idno);
}


