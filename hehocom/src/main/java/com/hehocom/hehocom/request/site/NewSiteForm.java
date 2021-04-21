package com.hehocom.hehocom.request.site;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hehocom.hehocom.entities.site.Statut;
import com.hehocom.hehocom.entities.site.Type;

public class NewSiteForm {

	@NotNull(message = "Un nom est requis")
	@NotBlank(message = "Un nom ne peut être vide")
	private String name;

	private String url;

	private Type type;

	private String Presentation;

	private Statut statut;

	private String bdd_name;

	private Timestamp date_creation;

	public NewSiteForm() {
	}

	public NewSiteForm(String name, String url, Type type, String Presentation, Statut statut, String bdd_name,
			Timestamp date_creation) {
		super();
		this.name = name;
		this.url = url;
		this.type = type;
		this.Presentation = Presentation;
		this.statut = statut;
		this.bdd_name = bdd_name;
		this.date_creation = date_creation;
	}

	public Timestamp getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Timestamp date_creation) {
		this.date_creation = date_creation;
	}

	public String getBdd_name() {
		return bdd_name;
	}

	public void setBdd_name(String bdd_name) {
		this.bdd_name = bdd_name;
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

	public String getPresentation() {
		return Presentation;
	}

	public void setPresentation(String Presentation) {
		this.Presentation = Presentation;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

}
