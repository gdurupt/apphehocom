package com.hehocom.hehocom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hehocom.hehocom.entities.service.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

	Services findAllById(Long id);

}
