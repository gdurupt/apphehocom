package com.hehocom.hehocom.services;

import java.util.List;

import javax.validation.Valid;

import com.hehocom.hehocom.entities.UserBySite.UserBySite;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.request.userBySite.UserBySiteForm;

public interface UserBySiteService {

	UserBySite addUserBySite(@Valid UserBySiteForm userByIdForm);

	List<Site> allSiteByIdUser(Member checkMember);

	void deleteAllUserByIdByIdSite(long idSite);

	void deleteAllUserByIdByIdUser(long idUser);

	List<Site> getAllSiteByIdUser(long idUser);

	void adUserBySite(String name, long idUser);

	void removeAllSiteByIdUser(long idUser, long idSite);

}
