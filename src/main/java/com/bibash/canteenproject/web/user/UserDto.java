package com.bibash.canteenproject.web.user;

import lombok.Data;

import com.bibash.canteenproject.core.enums.RoleType;
import com.bibash.canteenproject.core.enums.Status;

@Data
public class UserDto {

    private Long id;

    private String userName;

    private String token;

    private RoleType roleType;

    private Double walletAmount;

    private Status status;

    private String userCode;

    private String fullName;

    private String email;

    private String batch;
}
