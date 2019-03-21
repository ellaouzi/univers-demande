package com.fosagri.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosagri.model.entity.Student;
import com.fosagri.repository.student.StudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	public void removeStudent(Student student) {
		studentRepository.delete(student);
		
	}

}
