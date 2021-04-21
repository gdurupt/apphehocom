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

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.request.userBySite.UserBySiteForm;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.UserBySiteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class UserBySite {

	@Autowired
	private UserBySiteService UserBySiteService;

	@Autowired
	private AuthenticationService service;

	@PostMapping(value = "/userbysite")
	public @Valid com.hehocom.hehocom.entities.UserBySite.UserBySite addUserBySite(
			@Valid @RequestBody UserBySiteForm UserByIdForm) {
		return UserBySiteService.addUserBySite(UserByIdForm);
	}

	@GetMapping(value = "/userbysite/{name}/{idUser}")
	public void adUserBySite(@PathVariable String name, @PathVariable long idUser) {
		UserBySiteService.adUserBySite(name, idUser);
	}

	@GetMapping(value = "/userbysite/byUser")
	public @Valid List<Site> allSiteByIdUser() {
		Member CheckMember = this.service.getCurrentUser();
		return UserBySiteService.allSiteByIdUser(CheckMember);
	}

	@GetMapping(value = "/userbysite/byUser/{idUser}")
	public @Valid List<Site> allSiteByIdUser(@PathVariable long idUser) {
		return UserBySiteService.getAllSiteByIdUser(idUser);
	}

	@DeleteMapping(value = "/userbysite/byUser/{idUser}/{idSite}")
	public void removeSiteByIdUser(@PathVariable long idUser, @PathVariable long idSite) {
		UserBySiteService.removeAllSiteByIdUser(idUser, idSite);
	}
}
