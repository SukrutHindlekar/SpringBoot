package com.example.api.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.entities.OTP;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Long> {
	
	@Query("SELECT t FROM OTP t where t.id=:currentuserid")
	OTP fetchotpdetails(long currentuserid);
	
	@Modifying 
	@Transactional
	@Query("UPDATE OTP t SET t.tempotp=:otp1 where t.idno=:idno")
	int updateotp(int otp1, long idno);
}

