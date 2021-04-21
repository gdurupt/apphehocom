package com.hehocom.hehocom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.UserBySite.UserBySite;

@Repository
public interface UserBySiteRepository extends JpaRepository<UserBySite, Long> {

	List<UserBySite> findAllByIdUser(Long id);

	List<UserBySite> findAllByIdUser(long id);

	void deleteByIdSite(long idSite);

	List<UserBySite> findAllByIdSite(long idSite);

	@Transactional
	void deleteByIdUserAndIdSite(Long idUser, Long idSite);

	UserBySite findAllByIdUserAndIdSite(long idUser, Long id);

}
