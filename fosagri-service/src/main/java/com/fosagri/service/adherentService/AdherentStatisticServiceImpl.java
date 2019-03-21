package com.fosagri.service.adherentService;

import com.fosagri.repository.adherent.AdherentRepository;
import com.fosagri.repository.university.UniversityRepository;
import com.fosagri.service.universityStatisticService.UniversityStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdherentStatisticServiceImpl implements AdherentStatisticService{

	@Autowired
	private AdherentRepository adherentRepository;



	public Integer getNumOfAdherents() {
		return adherentRepository.getNumOfAdherents();
	}
}
