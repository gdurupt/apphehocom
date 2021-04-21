package com.hehocom.hehocom.entities.UserBySite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userBySite")
public class UserBySite {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "idUser")
	@NotNull(message = "Un idUser est requis")
	private long idUser;

	@Column(name = "idSite")
	@NotNull(message = "Un idSite est requis")
	private long idSite;

	public UserBySite() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdSite() {
		return idSite;
	}

	public void setIdSite(long idSite) {
		this.idSite = idSite;
	}

}
