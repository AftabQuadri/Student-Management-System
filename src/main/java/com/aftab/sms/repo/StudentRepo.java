package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Student;

@Repository

public interface StudentRepo extends JpaRepository<Student, Long> {
	Student findByRegistrationNumber(Long registrationNumber);

	List<Student> findAll();

	Student findByFirstName(String firstName);

	Student findByLastName(String lastName);
}
