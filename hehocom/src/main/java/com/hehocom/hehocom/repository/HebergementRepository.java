package com.hehocom.hehocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.hebergement.Hebergement;

@Repository
public interface HebergementRepository extends JpaRepository<Hebergement, Long> {

	Hebergement findByNameHberger(String name);

}
