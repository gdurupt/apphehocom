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

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.mission.Mission;
import com.hehocom.hehocom.request.mission.NewMissionRequest;
import com.hehocom.hehocom.request.mission.UpdateMissionRequest;
import com.hehocom.hehocom.services.AuthenticationService;
import com.hehocom.hehocom.services.MissionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/hehocom")
public class MissionController {

	@Autowired
	private MissionService missionService;

	@Autowired
	private AuthenticationService service;

	// Get all mission by idSite
	@GetMapping(value = "/mission/{idSite}/{statut}/{offset}")
	public List<Mission> getMemberById(@PathVariable Long idSite, @PathVariable String statut,
			@PathVariable int offset) {
		return missionService.getMissionByIdSiteAndType(idSite, statut, offset);
	}

	// Get all mission by idSite
	@GetMapping(value = "/mission/last/{idSite}")
	public Mission getLastMissionById(@PathVariable int idSite) {
		return missionService.getLastMissionByIdSite(idSite);
	}

	// Get all mission by idSite
	@GetMapping(value = "/mission/requeteclient/{idSite}")
	public List<Mission> getAllmissionClient(@PathVariable int idSite) {
		return missionService.getAllRequeteClient(idSite);
	}

	// Get all mission by type and idSite
	@GetMapping(value = "/mission/count/{idSite}")
	public int getCountMissionById(@PathVariable int idSite) {
		return missionService.getCountMissionByIdSite(idSite);
	}

	// add mission
	@PostMapping(value = "/mission")
	public Mission addMissionr(@RequestBody NewMissionRequest NewMission) {
		Member CheckMember = this.service.getCurrentUser();

		return missionService.addMission(NewMission, CheckMember);
	}

	// update mission
	@PutMapping(value = "/mission/{id}")
	public Mission updateMissionById(@PathVariable Long id, @RequestBody UpdateMissionRequest updateMission) {
		return missionService.updateMissionById(id, updateMission);
	}

	// delete mission
	@DeleteMapping(value = "/mission/{id}")
	public void deleteMissionById(@PathVariable long id) {
		missionService.deleteMissionById(id);
	}
}
