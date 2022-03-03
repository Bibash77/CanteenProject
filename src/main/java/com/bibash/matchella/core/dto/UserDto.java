package com.bibash.matchella.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.matchella.api.User.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

   public Double walletAmount;
    public User user;
}
