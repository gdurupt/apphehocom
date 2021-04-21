package com.hehocom.hehocom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.coment.Coment;

@Repository
public interface ComentRepository extends JpaRepository<Coment, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM coment WHERE id_site = :id_site ORDER BY id_coment DESC LIMIT 1 ")
	public Coment findLast(@Param(value = "id_site") int idSite);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM coment WHERE id_site = :id_site")
	int countAllByIdSite(@Param(value = "id_site") int idSite);

	@Query(nativeQuery = true, value = "SELECT * FROM coment WHERE id_site = :idsite ORDER BY id_coment DESC LIMIT 6 OFFSET :offset ")
	List<Coment> findAllByIdSiteby6(@Param(value = "idsite") Long idSite, @Param(value = "offset") int offset);
}
