package com.aftab.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aftab.sms.entities.Student;

@Repository

public interface StudentRepo extends JpaRepository<Student, Long> {
    // Example of a derived query method
    Student findByRegistrationNumber(Long registrationNumber);
    List<Student> findAll();
    Student findByFirstName(String firstName);
 //    @Query("SELECT s.lastName FROM Student s WHERE s.lastName = :lastname")
//    Student findStudentByLastName(@Param("lastname") String lastname);
//    @Query("SELECT s.firstName FROM Student s WHERE s.firstName = :firstname")
//    Student findStudentByFirstName(@Param("lastname") String firstname);
    Student findByLastName(String lastName);
}
