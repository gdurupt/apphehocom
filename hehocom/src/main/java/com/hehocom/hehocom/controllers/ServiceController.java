package com.hehocom.hehocom.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.request.service.ServiceForm;
import com.hehocom.hehocom.services.ServiceBySiteService;
import com.hehocom.hehocom.services.ServiceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class ServiceController {

	@Autowired
	private ServiceService ServiceService;

	@Autowired
	private ServiceBySiteService ServiceBySite;

	@GetMapping(value = "/service")
	public List<Services> getAllService() {
		return ServiceService.getAllService();
	}

	@DeleteMapping(value = "/service/{id}")
	public void deleteServiceById(@PathVariable long id) {
		ServiceBySite.deleteAllServiceBySiteWhitIdService(id);
		ServiceService.deleteServiceById(id);
	}

	@PostMapping(value = "/service")
	public @Valid Services addService(@RequestBody ServiceForm createServiceForm) {
		return ServiceService.addService(createServiceForm);
	}

	@PutMapping(value = "/service/{id}")
	public @Valid Void UpdateService(@RequestBody ServiceForm createServiceForm, @PathVariable long id) {
		return ServiceService.UpdateService(createServiceForm, id);
	}
}
