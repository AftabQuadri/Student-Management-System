package com.aftab.sms.binding;

import lombok.Data;

@Data
public class SearchRequest {
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private String branchName;
    private String courseName;
}
