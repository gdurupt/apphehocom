package com.hehocom.hehocom.request.member;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateMemberForm {

	private String username;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
	private Timestamp birthDate;

	public UpdateMemberForm() {

	}

	public UpdateMemberForm(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}