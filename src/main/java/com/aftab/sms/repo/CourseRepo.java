package com.aftab.sms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    // Example of a derived query method
//    List<Course> findByCourseName(String courseName);
    Optional<Course> findById(Long id);
    Course findByCourseName(String courseName);
    
}
