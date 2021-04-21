package com.hehocom.hehocom.request.coment;

public class NewComentRequest {

	private String content;

	private Long idSite;

	private String file;

	public NewComentRequest(String content, String status, Long idSite, Long idUser, String file) {
		super();
		this.content = content;
		this.idSite = idSite;
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
