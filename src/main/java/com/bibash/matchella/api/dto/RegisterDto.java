package com.bibash.matchella.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterDto {
	
	private String email;
	private String password;
	private String firstName;
	private Date dateOfBirth;
	private String referrerCode;
	
	private long gender;
	
	private boolean termsConditions;
	private boolean privacy;
}
