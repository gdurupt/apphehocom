package com.hehocom.hehocom.request.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateMemberForm {

	@NotNull(message = "Un nom est requis")
	@NotBlank(message = "Un nom ne peut être vide")
	private String username;

	@NotNull(message = "Un prénom est requis")
	@NotBlank(message = "Un prénom ne peut être vide")
	private String secondName;

	private String tel;

	public UpdateMemberForm() {
	}

	public UpdateMemberForm(String username, String tel, String secondName) {
		super();
		this.username = username;
		this.tel = tel;
		this.secondName = secondName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}