package com.hehocom.hehocom.request.ServiceBySite;

import javax.validation.constraints.NotNull;

public class ServiceBySiteForm {

	@NotNull(message = "Un nom est requis")
	private long idSite;

	@NotNull(message = "Un nom est requis")
	private Long idService;

	public ServiceBySiteForm(@NotNull(message = "Un nom est requis") long idSite,
			@NotNull(message = "Un nom est requis") Long idService) {
		super();
		this.idSite = idSite;
		this.idService = idService;
	}

	public long getIdSite() {
		return idSite;
	}

	public void setIdSite(long idSite) {
		this.idSite = idSite;
	}

	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

}
