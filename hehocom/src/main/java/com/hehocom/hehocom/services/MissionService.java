package com.hehocom.hehocom.services;

import java.util.List;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.mission.Mission;
import com.hehocom.hehocom.request.mission.NewMissionRequest;
import com.hehocom.hehocom.request.mission.UpdateMissionRequest;

public interface MissionService {

	List<Mission> getMissionByIdSite(Long idSite);

	Mission updateMissionById(Long id, UpdateMissionRequest updatemission);

	void deleteMissionById(long id);

	Mission getLastMissionByIdSite(int idSite);

	int getCountMissionByIdSite(int idSite);

	List<Mission> getMissionByIdSiteAndType(Long idSite, String statut, int offset);

	Mission addMission(NewMissionRequest newMission, Member checkMember);

	List<Mission> getAllRequeteClient(int idSite);

}
