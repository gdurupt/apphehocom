package com.hehocom.hehocom.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.UserBySite.UserBySite;
import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.member.Status;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.repository.SiteRepository;
import com.hehocom.hehocom.repository.UserBySiteRepository;
import com.hehocom.hehocom.request.site.NewSiteForm;
import com.hehocom.hehocom.services.SiteService;

@Service(value = "site")
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private UserBySiteRepository userBySiteRepository;

	@Override
	public Site addSite(NewSiteForm createSiteForm) {
		Site newSite = new Site();

		newSite.setName(createSiteForm.getName());
		newSite.setPresentation(null);
		newSite.setStatut(createSiteForm.getStatut());
		newSite.setType(createSiteForm.getType());
		newSite.setUrl(createSiteForm.getUrl());

		return siteRepository.save(newSite);
	}

	@Override
	public List<Site> getAllSite() {
		return siteRepository.findAll();
	}

	@Override
	public Optional<Site> getOneSite(Long id) {
		return siteRepository.findById(id);
	}

	@Override
	public Void UpdateSite(NewSiteForm createSiteForm, Long id) {
		Site siteChange = siteRepository.getOne(id);
		if (siteChange != null) {
			siteChange.setName(createSiteForm.getName());
			siteChange.setStatut(createSiteForm.getStatut());
			siteChange.setType(createSiteForm.getType());
			siteChange.setUrl(createSiteForm.getUrl());
			siteChange.setBdd_name(createSiteForm.getBdd_name());
			siteChange.setDateCreation(createSiteForm.getDate_creation());

			siteRepository.save(siteChange);
		}
		return null;
	}

	@Override
	public Void deleteOneSIte(Long id) {
		siteRepository.deleteById(id);
		return null;
	}

	@Override
	public Void UpdateHebergementSite(long idSite, long idHebergement) {
		Site siteChange = siteRepository.getOne(idSite);

		siteChange.setIdHebergement(idHebergement);

		siteRepository.save(siteChange);
		return null;
	}

	@Override
	public Site getSiteByName(String name, Member member) {
		if (member.getStatus() == Status.CLIENT) {
			List<UserBySite> allUserBySite = userBySiteRepository.findAllByIdUser(member.getId());

			Site site = siteRepository.findByNameSite(name);

			for (UserBySite oneSiteToUser : allUserBySite) {
				if (oneSiteToUser.getIdSite() == site.getId()) {
					return site;
				}
			}
			return null;
		} else {
			return siteRepository.findByNameSite(name);
		}

	}

	@Override
	public Void UpdateSiteFile(long idSite, MultipartFile file) {
		Site siteChange = siteRepository.getOne(idSite);

		if (file != null) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			siteChange.setPresentation(("ImageSiteId" + idSite + " - " + file.getOriginalFilename()));
		} else {
			siteChange.setPresentation(null);
		}

		siteRepository.save(siteChange);
		return null;
	}

	@Override
	public void resetHebergement(long id) {
		List<Site> site = siteRepository.findAllByIdHebergement(id);

		for (Site oneSite : site) {
			oneSite.setIdHebergement(null);
			siteRepository.save(oneSite);
		}

	}

	@Override
	public List<Site> siteByhebergement(long idHebergement) {
		return siteRepository.findAllByIdHebergement(idHebergement);
	}

	@Override
	public Void UpdateLots(long idSite, int lots) {
		Site siteChange = siteRepository.getOne(idSite);

		siteChange.setLots(lots);

		siteRepository.save(siteChange);
		return null;
	}

}
