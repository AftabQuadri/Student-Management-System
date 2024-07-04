package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
	List<Enrollment> findByEnrollmentId(Long enrollmentId);

	@Query("SELECT e FROM Enrollment e WHERE e.course.courseName = :courseName")
	List<Enrollment> findEnrollmentsByCourseName(@Param("courseName") String courseName);
}
