package com.hehocom.hehocom.request.userBySite;

import javax.validation.constraints.NotNull;

public class UserBySiteForm {

	@NotNull(message = "Un nom est requis")
	private long idSite;

	@NotNull(message = "Un nom est requis")
	private long idUser;

	public UserBySiteForm(@NotNull(message = "Un nom est requis") long idSite,
			@NotNull(message = "Un nom est requis") long idUser) {
		super();
		this.idSite = idSite;
		this.idUser = idUser;
	}

	public long getIdSite() {
		return idSite;
	}

	public void setIdSite(long idSite) {
		this.idSite = idSite;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

}
