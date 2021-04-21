package com.hehocom.hehocom.entities.hebergement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hebergement")
public class Hebergement {

	@Column(name = "id_hebergement")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nameHberger")
	private String nameHberger;

	@Column(name = "ip")
	private String ip;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private typeH type;

	@Column(name = "hebergeur")
	private String hebergeur;

	public Hebergement() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameHberger() {
		return nameHberger;
	}

	public void setNameHberger(String nameHberger) {
		this.nameHberger = nameHberger;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public typeH getType() {
		return type;
	}

	public void setType(typeH type) {
		this.type = type;
	}

	public String getHebergeur() {
		return hebergeur;
	}

	public void setHebergeur(String hebergeur) {
		this.hebergeur = hebergeur;
	}

}
