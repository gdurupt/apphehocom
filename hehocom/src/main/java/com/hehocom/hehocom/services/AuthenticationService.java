package com.hehocom.hehocom.services;

import org.springframework.security.core.Authentication;

import com.hehocom.hehocom.entities.member.Member;

public interface AuthenticationService {

	Member getCurrentUser();

	Authentication authentication(String email, String password);

	String login(Member user);

}
