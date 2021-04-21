package com.hehocom.hehocom.request.hebergement;

import com.hehocom.hehocom.entities.hebergement.typeH;

public class hebergementForm {

	private String nameHberger;

	private String ip;

	private typeH type;

	private String hebergeur;

	public hebergementForm(String nameHberger, String ip, typeH type, String hebergeur) {
		super();
		this.nameHberger = nameHberger;
		this.ip = ip;
		this.hebergeur = hebergeur;
		this.type = type;
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
