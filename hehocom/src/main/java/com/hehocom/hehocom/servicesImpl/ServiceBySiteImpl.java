package com.hehocom.hehocom.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.UserByService.ServiceBySite;
import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.repository.ServiceBySiteRepository;
import com.hehocom.hehocom.repository.ServiceRepository;
import com.hehocom.hehocom.request.ServiceBySite.ServiceBySiteForm;
import com.hehocom.hehocom.services.ServiceBySiteService;

@Service(value = "serviceBySite")
public class ServiceBySiteImpl implements ServiceBySiteService {

	@Autowired
	private ServiceBySiteRepository serviceBySiteRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public ServiceBySite addServiceBySite(@Valid ServiceBySiteForm serviceBySiteForm) {

		ServiceBySite ServiceBySite = new ServiceBySite();

		ServiceBySite.setIdService(serviceBySiteForm.getIdService());
		ServiceBySite.setIdSite(serviceBySiteForm.getIdSite());

		return serviceBySiteRepository.save(ServiceBySite);
	}

	@Override
	public @Valid List<Services> getAllServiceBySite(Long idSite) {
		List<ServiceBySite> ServiceBySite = serviceBySiteRepository.findAllByIdSite(idSite);
		List<Services> service = new ArrayList<Services>();
		Services OneService;

		for (ServiceBySite oneServiceBySite : ServiceBySite) {
			service.add(serviceRepository.findAllById(oneServiceBySite.getIdService()));
		}

		return service;
	}

	@Override
	public void deleteAllServiceBySite(long idSite) {
		List<ServiceBySite> ServiceBySite = serviceBySiteRepository.findAllByIdSite(idSite);

		for (ServiceBySite oneServiceBySite : ServiceBySite) {
			serviceBySiteRepository.deleteById(oneServiceBySite.getId());
		}
	}

	@Override
	public void deleteAllServiceBySiteWhitIdService(long id) {
		List<ServiceBySite> ServiceBySite = serviceBySiteRepository.findAllByIdService(id);

		for (ServiceBySite oneServiceBySite : ServiceBySite) {
			serviceBySiteRepository.deleteById(oneServiceBySite.getId());
		}
	}

	@Override
	public void deleteServiceBySite(Long idService, Long idSite) {
		serviceBySiteRepository.deleteByIdServiceAndIdSite(idService, idSite);
	}

}
