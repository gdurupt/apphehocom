package com.hehocom.hehocom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.site.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

	Site findById(long id);

	List<Site> findAllByIdHebergement(long id);

	@Query(nativeQuery = true, value = "SELECT * FROM site WHERE name = :name LIMIT 1")
	Site findByNameSite(@Param(value = "name") String name);

}
