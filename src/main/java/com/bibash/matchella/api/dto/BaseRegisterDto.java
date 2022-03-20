package com.bibash.matchella.api.dto;

import com.bibash.matchella.api.user.User;
import lombok.Data;

@Data
public class BaseRegisterDto {
	
	private User user;
	private RegisterDto registerDto;
	
}