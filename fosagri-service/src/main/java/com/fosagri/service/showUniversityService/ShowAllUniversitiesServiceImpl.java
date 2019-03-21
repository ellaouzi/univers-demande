package com.fosagri.service.showUniversityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosagri.model.entity.University;
import com.fosagri.repository.university.UniversityRepository;

@Service
public class ShowAllUniversitiesServiceImpl  implements ShowAllUniversitiesService{
	
	@Autowired
	private UniversityRepository universities;

	public List<University> getAllUniversities() {
		return universities.findAll();
	}

}
