package com.fosagri.service.showDemandeService;

import com.fosagri.model.entity.Demande;
import com.fosagri.repository.demande.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllDemandesServiceImpl implements ShowAllDemandesService {
	
	@Autowired
	private DemandeRepository demandeRepository;

	public List<Demande> getAllDemandes() {
		return demandeRepository.findAll();
	}

}
