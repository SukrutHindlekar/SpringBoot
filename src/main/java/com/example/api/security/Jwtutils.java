package com.example.api.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class Jwtutils {
	
	private String jwtsecret = "SECRETKEY";
	
	private int jwtExpirationMs = 604800000;
	
	public String generatejwtToken(Authentication authentication)
	{
		JwtDetailsimpl userPrincipal = (JwtDetailsimpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getEmailId()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
				.signWith(SignatureAlgorithm.HS512, jwtsecret).compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
