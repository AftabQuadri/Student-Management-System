package com.aftab.sms.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	private String courseName;
	private String courseDescription;
	private Integer credits;
	private String semester;
	private Integer year;
	private String fees;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Branch> branches;

	@OneToMany(mappedBy = "course")
	private Set<Enrollment> enrollments;

	@OneToMany(mappedBy = "course")
	private Set<Grade> grades;

}
