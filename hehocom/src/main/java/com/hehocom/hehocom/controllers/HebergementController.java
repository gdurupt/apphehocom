package com.hehocom.hehocom.controllers;

import java.util.List;

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

import com.hehocom.hehocom.entities.hebergement.Hebergement;
import com.hehocom.hehocom.request.hebergement.hebergementForm;
import com.hehocom.hehocom.services.HerbegementService;
import com.hehocom.hehocom.services.SiteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class HebergementController {

	@Autowired
	private HerbegementService hebergementService;

	@Autowired
	private SiteService siteService;

	// Récupération de tout les hebergeur
	@GetMapping(value = "/hebergement")
	public List<Hebergement> getAllHebergement() {
		return hebergementService.getHebergement();
	}

	// Récupération de tout les hebergeur
	@GetMapping(value = "/hebergement/{name}")
	public Hebergement getByNameHebergement(@PathVariable String name) {
		return hebergementService.getHebergementByName(name);
	}

	// Ajout d'un nouveau hebergeur
	@PostMapping(value = "/hebergement")
	public Hebergement addHebergement(@RequestBody hebergementForm hebergementForm) {
		return hebergementService.addHebergement(hebergementForm);
	}

	// modificiation d'un herbergeur
	@PutMapping(value = "/hebergement/{id}")
	public Hebergement udpateHebergementById(@PathVariable long id, @RequestBody hebergementForm hebergementForm) {
		return hebergementService.updateHebergement(id, hebergementForm);
	}

	// Suppression d'un hebergeur
	@DeleteMapping(value = "/hebergement/{id}")
	public Void deleteHebergementById(@PathVariable long id) {
		siteService.resetHebergement(id);

		return hebergementService.deleteHebergement(id);
	}
}
