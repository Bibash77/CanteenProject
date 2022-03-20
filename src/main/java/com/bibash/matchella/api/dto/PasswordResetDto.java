package com.bibash.matchella.api.dto;

import lombok.Data;

@Data
public class PasswordResetDto {

	private long captchaId;
	private String captchaText;
	private String email;
}
