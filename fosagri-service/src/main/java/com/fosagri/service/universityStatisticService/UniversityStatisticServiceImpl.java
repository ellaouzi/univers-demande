package com.fosagri.service.universityStatisticService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosagri.repository.university.UniversityRepository;

@Service
public class UniversityStatisticServiceImpl implements UniversityStatisticService{

	@Autowired
	private UniversityRepository universityRepository;
	public Integer getNumOfStudentsForUniversity(Integer universityId) {	
		return universityRepository.getNumOfStudentForUniversities(universityId);
	}

}
