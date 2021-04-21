package com.hehocom.hehocom.entities.site;

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
@Table(name = "site")
public class Site {

	@Column(name = "id_site")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_hebergement")
	private Long idHebergement;

	@Column(name = "creation_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
	private Timestamp dateCreation;

	@Column(name = "name")
	@NotNull(message = "Un nom est requis")
	@NotBlank(message = "Un nom de site ne peut être vide")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "bdd_name")
	private String bdd_name;

	@Column(name = "lots")
	private int lots;

	@Column(name = "type")
	@NotNull(message = "Un type est requis")
	@Enumerated(EnumType.STRING)
	private Type type;

	@Column(name = "Presentation")
	private String presentation;

	@Column(name = "statut")
	@NotNull(message = "Un statut est requis")
	@Enumerated(EnumType.STRING)
	private Statut statut;

	public String getBdd_name() {
		return bdd_name;
	}

	public void setBdd_name(String bdd_name) {
		this.bdd_name = bdd_name;
	}

	public Site() {
		super();
		this.dateCreation = new Timestamp(System.currentTimeMillis());
		this.lots = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdHebergement() {
		return idHebergement;
	}

	public void setIdHebergement(Long idHebergement) {
		this.idHebergement = idHebergement;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public int getLots() {
		return lots;
	}

	public void setLots(int lots) {
		this.lots = lots;
	}

}
