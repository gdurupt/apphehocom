package com.hehocom.hehocom.request.member;

import com.sun.istack.NotNull;

public class LoginForm {
	@NotNull
	private String email;

	@NotNull
	private String password;

	public LoginForm() {
	}

	public LoginForm(String username, String password) {
		this.email = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}