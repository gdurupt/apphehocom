package com.hehocom.hehocom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.mission.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

	List<Mission> findAllByIdSite(long idSite);

	@Query(nativeQuery = true, value = "SELECT * FROM mission WHERE id_site = :id_site AND statut = 'FAIT' ORDER BY id DESC LIMIT 1 ")
	public Mission findByIdSite(@Param(value = "id_site") int idSite);

	@Query(nativeQuery = true, value = "SELECT * FROM mission WHERE id_site = :id_site AND statut = 'REQUETECLIENT'")
	List<Mission> findallRequeteClient(@Param(value = "id_site") int idSite);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM mission WHERE id_site = :id_site")
	int countAllByIdSite(@Param(value = "id_site") int idSite);

	@Query(nativeQuery = true, value = "SELECT * FROM mission WHERE id_site = :idsite AND statut = :statut ORDER BY id DESC LIMIT 6 OFFSET :offset")
	List<Mission> findAllByIdSiteAndStatutby6(@Param(value = "idsite") Long idSite,
			@Param(value = "statut") String statut, @Param(value = "offset") int offset);

}
