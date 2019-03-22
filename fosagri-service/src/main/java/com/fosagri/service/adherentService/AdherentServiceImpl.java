package com.fosagri.service.adherentService;

import com.fosagri.model.entity.Adherent;
import com.fosagri.repository.adherent.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdherentServiceImpl implements AdherentService {

	@Autowired
	private AdherentRepository adherentRepository;

	public void addAdherent(Adherent adherent) {
		adherentRepository.save(adherent);
	}

	public Adherent findAdherentByPpr(String ppr) {
return adherentRepository.findAdherentByPpr(ppr);
	}
}
