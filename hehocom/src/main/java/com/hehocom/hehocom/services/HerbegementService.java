package com.hehocom.hehocom.services;

import java.util.List;

import com.hehocom.hehocom.entities.hebergement.Hebergement;
import com.hehocom.hehocom.request.hebergement.hebergementForm;

public interface HerbegementService {

	List<Hebergement> getHebergement();

	Hebergement addHebergement(hebergementForm hebergementForm);

	Hebergement updateHebergement(long id, hebergementForm hebergementForm);

	Void deleteHebergement(long id);

	Hebergement getHebergementByName(String name);

}
