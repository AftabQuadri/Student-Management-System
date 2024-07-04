package com.aftab.sms.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long libraryId;
	private String bookTitle;
	private String author;
	private String isbn;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	private LocalDate issueDate;
	private LocalDate returnDate;
}
