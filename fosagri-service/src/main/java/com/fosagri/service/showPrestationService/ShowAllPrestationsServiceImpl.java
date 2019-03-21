package com.fosagri.service.showPrestationService;

import com.fosagri.model.entity.Prestation;
import com.fosagri.repository.prestation.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllPrestationsServiceImpl implements ShowAllPrestationsService {
	
	@Autowired
	private PrestationRepository prestationRepository;

	public List<Prestation> getAllPrestations() {
		return prestationRepository.findAll();
	}

}
