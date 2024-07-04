package com.aftab.sms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Branch {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    
    private String branchName;
    
    private String courseName;
    private String branchDescription;
    }
