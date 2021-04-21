package com.hehocom.hehocom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hehocom.hehocom.entities.member.Member;
import com.hehocom.hehocom.entities.site.Site;
import com.hehocom.hehocom.request.site.NewSiteForm;

public interface SiteService {

	Site addSite(NewSiteForm createSiteForm);

	List<Site> getAllSite();

	Optional<Site> getOneSite(Long id);

	Void UpdateSite(NewSiteForm createSiteForm, Long id);

	Void deleteOneSIte(Long id);

	Void UpdateHebergementSite(long idSite, long idHebergement);

	Site getSiteByName(String name, Member member);

	Void UpdateSiteFile(long idSite, MultipartFile file);

	void resetHebergement(long id);

	List<Site> siteByhebergement(long idHebergement);

	Void UpdateLots(long idSite, int lots);

}
