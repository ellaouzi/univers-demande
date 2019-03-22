package com.fosagri.service.adherentService;

import com.fosagri.model.entity.Adherent;
import com.fosagri.repository.adherent.AdherentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAllAdherentsServiceImpl implements ShowAllAdherentsService{
	
	@Autowired
	private AdherentRepository adherentRepository;

	public List<Adherent> getAllAdherents() {
		return adherentRepository.findAll();
	}

}
