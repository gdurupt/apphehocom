package com.hehocom.hehocom.request.service;

public class ServiceForm {

	private String nom;

	private String content;

	public ServiceForm(String nom, String content) {
		super();
		this.nom = nom;
		this.content = content;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
