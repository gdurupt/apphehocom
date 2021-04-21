package com.hehocom.hehocom.entities.mission;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "mission")
public class Mission {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "content")
	@NotNull(message = "Une mission est requis")
	@NotBlank(message = "Une mission ne peut être vide")
	private String content;

	@Column(name = "name")
	@NotNull(message = "Un nom est requis")
	@NotBlank(message = "Un nom ne peut être vide")
	private String name;

	@Column(name = "idSite")
	@NotNull(message = "il faut un id de site")
	private Long idSite;

	@Column(name = "idUser")
	@NotNull(message = "il faut un id utilisateur")
	private Long idUser;

	@Column(name = "duree")
	private String duree;

	@Column(name = "statut")
	@NotNull(message = "Un statut est requis")
	@Enumerated(EnumType.STRING)
	private Statut statut;

	@Column(name = "type")
	@NotNull(message = "Un statut est requis")
	@Enumerated(EnumType.STRING)
	private Type type;

	@Column(name = "creation_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
	private Timestamp dateCreation;

	public Mission() {
		super();
		this.dateCreation = new Timestamp(System.currentTimeMillis());
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public void setDateCreation(Timestamp timestamp) {
		// TODO Auto-generated method stub

	}

}
