package com.setu.billsystem.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.setu.billsystem.model.User;
import com.setu.billsystem.service.CustomUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	SetuJwtHelper setuJwtHelper;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Value("${schemeId}")
	String schemeId;
	@Value("${secret}")
	String secret;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization=request.getHeader("Authorization");
		
		DecodedJWT jwt = null;
		
		if(authorization!=null && authorization.startsWith("Bearer ")) {
			try {
			jwt = setuJwtHelper.verifyBearerToken(authorization);
			}catch(Exception e) {
				System.out.println(e);
			}
			System.out.println(jwt);	
		}
		
		if(jwt!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			String scheme = jwt.getAudience().get(0);
			UserDetails userDetails = userDetailsService.loadUserByUsername(scheme);
			if(scheme.equals(schemeId)) {
				UsernamePasswordAuthenticationToken authReq
				 = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authReq);
				
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
