package com.example.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.api.Service.UserDetailsServiceimpl;

@Component
public class TokenFilter extends OncePerRequestFilter {

	@Autowired
	private Jwtutils jwtutils;
	
	@Autowired
	private UserDetailsServiceimpl userDetailsService;
	
	private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if(jwt!=null && jwtutils.validateJwtToken(jwt)) {
				String username = jwtutils.getUserNameFromJwtToken(jwt);
				log.info("username is "+username);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				log.info("userdetails object "+userDetails.toString());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
						userDetails.getPassword(), userDetails.getAuthorities());
				log.info("get authorities "+userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				log.info("After setDetails method");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.error("cannot authenticate user: {}",e);
		}
		log.info("before filter chain");
		filterChain.doFilter(request, response);
		log.info("after filter chain");
		}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			log.info("checking bearer token");
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}
