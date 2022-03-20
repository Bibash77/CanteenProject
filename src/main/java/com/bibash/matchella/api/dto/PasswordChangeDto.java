package com.bibash.matchella.api.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {

	private String email;
	private String password;
	private String token;
}
