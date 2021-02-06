package com.hehocom.hehocom.request.member;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.hehocom.hehocom.entities.member.Status;

public class UpdateMemberStatusForm {

	@Enumerated(EnumType.STRING)
	private Status status;

	public UpdateMemberStatusForm() {

	}

	public UpdateMemberStatusForm(Status status) {
		super();
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setUsername(Status status) {
		this.status = status;
	}

}
