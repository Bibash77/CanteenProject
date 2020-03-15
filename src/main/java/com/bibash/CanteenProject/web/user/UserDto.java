package com.bibash.CanteenProject.web.user;

import lombok.Data;
import org.hibernate.usertype.UserType;

import com.bibash.CanteenProject.core.enums.RoleType;
import com.bibash.CanteenProject.core.enums.Status;

@Data
public class UserDto {

    private Long id;

    private String userName;

    private String token;

    private RoleType roleType;

    private Double walletAmount;

    private Status status;

    private String userCode;
}
