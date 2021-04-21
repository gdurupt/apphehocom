package com.hehocom.hehocom.servicesImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.mission.Mission;
import com.hehocom.hehocom.repository.MissionRepository;
import com.hehocom.hehocom.request.mission.NewMissionRequest;
import com.hehocom.hehocom.request.mission.UpdateMissionRequest;
import com.hehocom.hehocom.services.MissionService;

@Service
public class MissionServiceImpl implements MissionService {

	@Autowired
	private MissionRepository missionRepository;

	@Override
	public List<Mission> getMissionByIdSite(Long idSite) {
		return missionRepository.findAllByIdSite(idSite);
	}

	@Override
	public Mission addMission(NewMissionRequest newMission, Member checkMember) {
		Mission mission = new Mission();

		mission.setStatut(newMission.getStatus());
		mission.setIdUser(checkMember.getId());
		mission.setType(newMission.getType());
		mission.setContent(newMission.getContent());
		mission.setIdSite(newMission.getIdSite());
		mission.setName(newMission.getName());
		mission.setDuree(newMission.getDuree());

		missionRepository.save(mission);

		return mission;
	}

	@Override
	public Mission updateMissionById(Long id, UpdateMissionRequest updatemission) {
		Mission mission = missionRepository.getOne(id);

		mission.setDateCreation(new Timestamp(System.currentTimeMillis()));
		mission.setType(updatemission.getType());
		mission.setContent(updatemission.getContent());
		mission.setIdSite(updatemission.getIdSite());
		mission.setStatut(updatemission.getStatus());
		mission.setName(updatemission.getName());
		mission.setDuree(updatemission.getDuree());

		missionRepository.save(mission);
		return null;
	}

	@Override
	public void deleteMissionById(long id) {
		missionRepository.deleteById(id);
	}

	@Override
	public Mission getLastMissionByIdSite(int idSite) {
		return missionRepository.findByIdSite(idSite);
	}

	@Override
	public int getCountMissionByIdSite(int idSite) {

		return missionRepository.countAllByIdSite(idSite);
	}

	@Override
	public List<Mission> getMissionByIdSiteAndType(Long idSite, String statut, int offset) {
		offset = offset * 6;
		return missionRepository.findAllByIdSiteAndStatutby6(idSite, statut, offset);
	}

	@Override
	public List<Mission> getAllRequeteClient(int idSite) {
		return missionRepository.findallRequeteClient(idSite);
	}

}
