package com.hehocom.hehocom.request.email;

public class lostPassword {

	private String email;

	public lostPassword(String email) {
		super();
		this.email = email;
	}

	public lostPassword() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
