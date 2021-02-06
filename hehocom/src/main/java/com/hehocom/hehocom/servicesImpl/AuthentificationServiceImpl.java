package com.hehocom.hehocom.servicesImpl;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.hehocom.hehocom.security.SecurityConstants.EXPIRATION_TIME;
import static com.hehocom.hehocom.security.SecurityConstants.SECRET;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.MemberService;

@Service
public class AuthentificationServiceImpl implements AuthenticationService {

	public static final int SIZE_TMP_CODE = 10;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private MemberService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Authentication authentication(String email, String password) {
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));
	}

	@Override
	public String login(Member user) {
		return JWT.create().withSubject(user.getEmail())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
	}

	@Override
	public Member getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.userService.getMemberByEmail((String) authentication.getPrincipal());

	}
}