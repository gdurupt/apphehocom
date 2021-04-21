package com.hehocom.hehocom.services;

import java.util.List;

import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.request.service.ServiceForm;

public interface ServiceService {

	List<Services> getAllService();

	void deleteServiceById(long id);

	Services addService(ServiceForm createServiceForm);

	Void UpdateService(ServiceForm createServiceForm, long id);

}
