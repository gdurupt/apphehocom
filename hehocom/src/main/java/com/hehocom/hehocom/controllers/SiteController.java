package com.hehocom.hehocom.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.request.site.NewSiteForm;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.ServiceBySiteService;
import com.hehocom.hehocom.services.SiteService;
import com.hehocom.hehocom.services.UserBySiteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@Autowired
	private UserBySiteService UserBySiteService;

	@Autowired
	private ServiceBySiteService ServiceBySite;

	@Autowired
	private AuthenticationService service;

	@PostMapping(value = "/site/add")
	public @Valid Site CreateSite(@Valid @RequestBody NewSiteForm createSiteForm) {
		return siteService.addSite(createSiteForm);
	}

	@GetMapping(value = "/sites")
	public List<Site> getAllSite() {
		return siteService.getAllSite();
	}

	@GetMapping(value = "/sites/{name}")
	public Site getOneSiteByName(@PathVariable String name) {
		Member member = this.service.getCurrentUser();
		return siteService.getSiteByName(name, member);
	}

	@GetMapping(value = "/site/{id}")
	public Optional<Site> getOneSite(@PathVariable long id) {
		return siteService.getOneSite(id);
	}

	@PutMapping(value = "/site/update/{id}")
	public @Valid Void UpdateOneSite(@Valid @RequestBody NewSiteForm createSiteForm, @PathVariable long id) {
		return siteService.UpdateSite(createSiteForm, id);
	}

	@GetMapping(value = "/site/update/{idSite}/{idHebergement}")
	public @Valid Void UpdateHebergementSite(@PathVariable long idSite, @PathVariable long idHebergement) {
		return siteService.UpdateHebergementSite(idSite, idHebergement);
	}

	@GetMapping(value = "/site/hebergement/{idHebergement}")
	public @Valid List<Site> siteByHebergement(@PathVariable long idHebergement) {
		return siteService.siteByhebergement(idHebergement);
	}

	@PutMapping(value = "/site/update/lots/{lots}/{idSite}")
	public @Valid Void UpdateServiceSite(@PathVariable long idSite, @PathVariable int lots) {
		return siteService.UpdateLots(idSite, lots);
	}

	@PutMapping(value = "/site/update/{idSite}/file")
	public @Valid Void UpdateFileSite(@PathVariable long idSite, @RequestPart("file") MultipartFile file) {

		Path copyLocationFile = Paths.get("D:\\ProjetPro\\HehocomFront\\src\\assets\\image_presentation"
				+ File.separator + StringUtils.cleanPath("ImageSiteId" + idSite + " - " + file.getOriginalFilename()));
		try {
			Files.copy(file.getInputStream(), copyLocationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return siteService.UpdateSiteFile(idSite, file);
	}

	@DeleteMapping(value = "/site/{id}")
	public Void DeleteOneSite(@PathVariable long id) {
		UserBySiteService.deleteAllUserByIdByIdSite(id);

		ServiceBySite.deleteAllServiceBySite(id);

		return siteService.deleteOneSIte(id);
	}
}
