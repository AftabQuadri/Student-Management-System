package com.aftab.sms.entities;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registrationNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate enrollmentDate;
	private LocalDate dateOfBirth;
	@Column(updatable = false)
	private LocalDate graduationDate;
	private String phoneNumber;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	private Blob studentImage;
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "student")
	private Set<Enrollment> enrollments;

	@OneToMany(mappedBy = "student")
	private Set<Grade> grades;

	private String fees;

	@OneToMany(mappedBy = "student")
	private Set<Library> library;

	public void setStudentImage(byte[] studentImageBytes) {
		try {
			this.studentImage = new SerialBlob(studentImageBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
