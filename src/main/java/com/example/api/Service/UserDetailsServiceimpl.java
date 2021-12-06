package com.example.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.api.Repository.UserRepository;
import com.example.api.entities.Users;
import com.example.api.security.JwtDetailsimpl;

@Service
public class UserDetailsServiceimpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = userRepository.getuserdetails(username);
		if(user == null) {
			throw new UsernameNotFoundException("No user found with email");
		}
		return JwtDetailsimpl.build(user);
	}

}
