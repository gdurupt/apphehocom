package com.hehocom.hehocom.request.Jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.hehocom.hehocom.entities.member.Member;

public class JwtResponse {

	private Member user;

	private String token;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse() {
	}

	public JwtResponse(Member user, String token, Collection<? extends GrantedAuthority> authorities) {
		this.user = user;
		this.token = token;
		this.authorities = authorities;
	}

	public Member getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
