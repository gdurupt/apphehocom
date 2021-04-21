package com.hehocom.hehocom.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.UserByService.ServiceBySite;

@Repository
public interface ServiceBySiteRepository extends JpaRepository<ServiceBySite, Long> {

	List<ServiceBySite> findAllByIdSite(long idSite);

	List<ServiceBySite> findAllByIdService(long id);

	@Transactional
	void deleteByIdServiceAndIdSite(Long idService, Long idSite);

}