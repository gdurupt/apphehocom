package com.hehocom.hehocom.services;

import java.util.List;

import javax.validation.Valid;

import com.hehocom.hehocom.entities.UserByService.ServiceBySite;
import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.request.ServiceBySite.ServiceBySiteForm;

public interface ServiceBySiteService {

	ServiceBySite addServiceBySite(@Valid ServiceBySiteForm serviceBySiteForm);

	List<Services> getAllServiceBySite(Long idSite);

	void deleteAllServiceBySite(long id);

	void deleteAllServiceBySiteWhitIdService(long id);

	void deleteServiceBySite(Long idService, Long idSite);

}
