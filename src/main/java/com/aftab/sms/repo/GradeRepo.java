package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Grade;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
	List<Grade> findByGradeId(Long gradeId);

	@Query("SELECT g FROM Grade g WHERE g.course.courseName = :courseName")
	List<Grade> findGradesByCourseName(@Param("courseName") String courseName);
}
