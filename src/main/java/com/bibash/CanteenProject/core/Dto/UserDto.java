package com.bibash.CanteenProject.core.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.enums.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

   public Double walletAmount;
    public User user;
}
