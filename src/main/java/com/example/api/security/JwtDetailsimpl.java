package com.example.api.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.api.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtDetailsimpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	public long getId() {
		return id;
	}

	public JwtDetailsimpl(long id) {
		super();
		this.id = id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;	
	
	public JwtDetailsimpl(String firstName, String lastName, String emailId, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.authorities = authorities;
	}
	
	public JwtDetailsimpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static JwtDetailsimpl build(Users user) {
		
		List<GrantedAuthority> authorities = new LinkedList<>();
		
		authorities.add(new SimpleGrantedAuthority(user.getPhoneNo()));
		
		return new JwtDetailsimpl(user.getFirstName(), user.getLastname(), user.getEmailId(), user.getPassword(), authorities);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JwtDetailsimpl admin = (JwtDetailsimpl) o;
		return Objects.equals(id, admin.getId());
	}
	
	@Override
	public String toString() {
		return "JwtDetailsimpl [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", password=" + password + ", authorities=" + authorities + "]";
	}
	
}
