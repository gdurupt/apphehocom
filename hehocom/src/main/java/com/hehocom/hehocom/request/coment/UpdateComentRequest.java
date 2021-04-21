package com.hehocom.hehocom.request.coment;

public class UpdateComentRequest {

	private String content;

	private String file;

	public UpdateComentRequest(String content, String file) {
		super();
		this.content = content;
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
