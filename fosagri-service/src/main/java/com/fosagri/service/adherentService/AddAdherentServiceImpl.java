package com.fosagri.service.adherentService;

import com.fosagri.model.entity.Adherent;
import com.fosagri.repository.adherent.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAdherentServiceImpl implements AddAdherentService  {

	@Autowired
	private AdherentRepository adherentRepository;
	
	public void addAdherent(Adherent adherentDAO) {
		Adherent adherent = new Adherent();
		adherent.setPrenom(adherentDAO.getPrenom());
		adherent.setNom(adherentDAO.getNom());
		adherent.setPpr(adherentDAO.getPpr());
		adherent.setAge(adherentDAO.getAge());
		adherent.setPosition(adherentDAO.getPosition());
		adherent.setGender(adherentDAO.getGender());
		adherentRepository.save(adherent);
	}
	
}
