package com.hehocom.hehocom.request.mission;

import com.hehocom.hehocom.entities.mission.Statut;
import com.hehocom.hehocom.entities.mission.Type;

public class NewMissionRequest {

	private Statut Status;

	private Type type;

	private Long idSite;

	private String content;

	private String name;

	private String duree;

	public NewMissionRequest(Statut statut, Type type, Long idSite, String content, String name, String duree) {
		super();
		this.Status = statut;
		this.type = type;
		this.idSite = idSite;
		this.content = content;
		this.name = name;
		this.duree = duree;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Statut getStatus() {
		return Status;
	}

	public void setStatus(Statut status) {
		Status = status;
	}

}
