package com.hehocom.hehocom.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.UserBySite.UserBySite;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.member.Status;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.repository.SiteRepository;
import com.hehocom.hehocom.repository.UserBySiteRepository;
import com.hehocom.hehocom.request.userBySite.UserBySiteForm;
import com.hehocom.hehocom.services.UserBySiteService;

@Service
public class UserBySiteServiceImpl implements UserBySiteService {

	@Autowired
	private UserBySiteRepository userBySiteRepository;

	@Autowired
	private SiteRepository siteRepository;

	@Override
	public UserBySite addUserBySite(@Valid UserBySiteForm userByIdForm) {
		UserBySite newUserBySite = new UserBySite();

		newUserBySite.setIdSite(userByIdForm.getIdSite());
		newUserBySite.setIdUser(userByIdForm.getIdUser());

		userBySiteRepository.save(newUserBySite);

		return newUserBySite;
	}

	@Override
	public @Valid List<Site> allSiteByIdUser(Member checkMember) {
		List<Site> site = new ArrayList<Site>();

		if (checkMember.getStatus() != Status.CLIENT) {
			return siteRepository.findAll();
		}

		List<UserBySite> allUserBySite = userBySiteRepository.findAllByIdUser(checkMember.getId());
		for (UserBySite oneSiteToUser : allUserBySite) {
			Site siteToadd = siteRepository.findById(oneSiteToUser.getIdSite());
			if (siteToadd != null) {
				site.add(siteToadd);
			}
		}

		return site;
	}

	@Override
	public void deleteAllUserByIdByIdSite(long idSite) {
		List<UserBySite> allUserBySite = userBySiteRepository.findAllByIdSite(idSite);

		for (UserBySite oneSiteToUser : allUserBySite) {
			userBySiteRepository.deleteById(oneSiteToUser.getId());
		}
	}

	@Override
	public void deleteAllUserByIdByIdUser(long idUser) {
		List<UserBySite> allUserBySite = userBySiteRepository.findAllByIdUser(idUser);

		for (UserBySite oneSiteToUser : allUserBySite) {
			userBySiteRepository.deleteById(oneSiteToUser.getId());
		}
	}

	@Override
	public List<Site> getAllSiteByIdUser(long idUser) {
		List<Site> site = new ArrayList<Site>();

		userBySiteRepository.findAllByIdUser(idUser);

		List<UserBySite> allUserBySite = userBySiteRepository.findAllByIdUser(idUser);
		for (UserBySite oneSiteToUser : allUserBySite) {
			Site siteToadd = siteRepository.findById(oneSiteToUser.getIdSite());
			if (siteToadd != null) {
				site.add(siteToadd);
			}
		}

		return site;
	}

	@Override
	public void adUserBySite(String name, long idUser) {
		Site site = siteRepository.findByNameSite(name);

		UserBySite checkUserBySite = userBySiteRepository.findAllByIdUserAndIdSite(idUser, site.getId());

		if (checkUserBySite == null) {
			UserBySite newUserBySite = new UserBySite();

			newUserBySite.setIdSite(site.getId());
			newUserBySite.setIdUser(idUser);

			userBySiteRepository.save(newUserBySite);
		}

	}

	@Override
	public void removeAllSiteByIdUser(long idUser, long idSite) {
		this.userBySiteRepository.deleteByIdUserAndIdSite(idUser, idSite);

	}

}
