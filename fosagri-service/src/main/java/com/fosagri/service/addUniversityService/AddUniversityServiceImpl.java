package com.fosagri.service.addUniversityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosagri.model.entity.University;
import com.fosagri.repository.university.UniversityRepository;

@Service
public class AddUniversityServiceImpl implements AddUniversityService  {

	@Autowired
	private UniversityRepository universityRepository;
	
	public void addUniversity(University universityDAO) {
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		university.setUniversityCity(universityDAO.getUniversityCity());
		
		universityRepository.save(university);
	}
	
}
