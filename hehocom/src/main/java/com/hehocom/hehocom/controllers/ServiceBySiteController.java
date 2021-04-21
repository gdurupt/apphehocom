package com.hehocom.hehocom.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hehocom.hehocom.entities.UserByService.ServiceBySite;
import com.hehocom.hehocom.entities.service.Services;
import com.hehocom.hehocom.request.ServiceBySite.ServiceBySiteForm;
import com.hehocom.hehocom.services.ServiceBySiteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class ServiceBySiteController {

	@Autowired
	private ServiceBySiteService ServiceBySite;

	@PostMapping(value = "/serviceBySite")
	public @Valid ServiceBySite addServiceBySite(@Valid @RequestBody ServiceBySiteForm ServiceBySiteForm) {
		return ServiceBySite.addServiceBySite(ServiceBySiteForm);
	}

	@GetMapping(value = "/serviceBySite/{idSite}")
	public @Valid List<Services> allServiceBySite(@PathVariable Long idSite) {
		return ServiceBySite.getAllServiceBySite(idSite);
	}

	@DeleteMapping(value = "/serviceBySite/{idService}/{idSite}")
	public @Valid void removeServiceBySite(@PathVariable Long idSite, @PathVariable Long idService) {
		ServiceBySite.deleteServiceBySite(idService, idSite);
	}
}
