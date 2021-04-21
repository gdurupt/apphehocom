package com.hehocom.hehocom.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hehocom.hehocom.entities.hebergement.Hebergement;
import com.hehocom.hehocom.repository.HebergementRepository;
import com.hehocom.hehocom.request.hebergement.hebergementForm;
import com.hehocom.hehocom.services.HerbegementService;

@Service
public class HebergementServiceImpl implements HerbegementService {

	@Autowired
	private HebergementRepository hebergementRepository;

	@Override
	public List<Hebergement> getHebergement() {
		return hebergementRepository.findAll();
	}

	@Override
	public Hebergement addHebergement(hebergementForm hebergementForm) {
		Hebergement hebergement = new Hebergement();

		Hebergement check = hebergementRepository.findByNameHberger(hebergementForm.getNameHberger());

		if (check != null) {
			return null;
		}

		hebergement.setIp(hebergementForm.getIp());
		hebergement.setNameHberger(hebergementForm.getNameHberger());
		hebergement.setType(hebergementForm.getType());
		hebergement.setHebergeur(hebergementForm.getHebergeur());

		return hebergementRepository.save(hebergement);

	}

	@Override
	public Hebergement updateHebergement(long id, hebergementForm hebergementForm) {
		Hebergement hebergement = hebergementRepository.getOne(id);

		Hebergement check = hebergementRepository.findByNameHberger(hebergementForm.getNameHberger());

		if (check != null) {
			return null;
		}

		hebergement.setIp(hebergementForm.getIp());
		hebergement.setNameHberger(hebergementForm.getNameHberger());
		hebergement.setType(hebergementForm.getType());
		hebergement.setHebergeur(hebergementForm.getHebergeur());

		return hebergementRepository.save(hebergement);
	}

	@Override
	public Void deleteHebergement(long id) {
		hebergementRepository.deleteById(id);
		return null;
	}

	@Override
	public Hebergement getHebergementByName(String name) {
		// TODO Auto-generated method stub
		return hebergementRepository.findByNameHberger(name);
	}

}
