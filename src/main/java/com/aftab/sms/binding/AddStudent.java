package com.aftab.sms.binding;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.aftab.sms.entities.Address;

import lombok.Data;
@Data
public class AddStudent {
	 private String firstName;
	    private String middleName;
	    private String lastName;
	    private String gender;
	    private LocalDate dateOfBirth;
	    private String phoneNumber;
	    private String branch;
	    private String course;
	    private MultipartFile studentImage;
	    private Address address;


}
