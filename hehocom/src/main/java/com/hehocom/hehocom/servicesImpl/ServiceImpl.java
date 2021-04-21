package com.hehocom.hehocom.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.repository.ServiceRepository;
import com.hehocom.hehocom.request.service.ServiceForm;
import com.hehocom.hehocom.services.ServiceService;

@Service(value = "service")
public class ServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public List<Services> getAllService() {

		return serviceRepository.findAll();
	}

	@Override
	public void deleteServiceById(long id) {
		serviceRepository.deleteById(id);
	}

	@Override
	public Services addService(ServiceForm createServiceForm) {
		Services service = new Services();

		service.setContent(createServiceForm.getContent());
		service.setNom(createServiceForm.getNom());

		return serviceRepository.save(service);
	}

	@Override
	public Void UpdateService(ServiceForm createServiceForm, long id) {
		Services service = serviceRepository.getOne(id);

		service.setContent(createServiceForm.getContent());
		service.setNom(createServiceForm.getNom());

		serviceRepository.save(service);
		return null;
	}

}
