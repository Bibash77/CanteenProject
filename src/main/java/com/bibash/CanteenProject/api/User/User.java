package com.bibash.CanteenProject.api.User;

import com.bibash.CanteenProject.api.standards.Standard;
import com.bibash.CanteenProject.core.enums.RoleType;
import com.bibash.CanteenProject.core.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Table(name="user")
public class User {

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "Full_Name")
    private String fullName;

    @Column(nullable = false)
    private RoleType roleType;

    private String password;

    private List<Standard> standardList;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Transient
    private MultipartFile studentImage;

    private String studentImagePath;

    private List<String> authorityList; //access of feautures/tab


    public String getUsername() {
        return this.username;
    }

}
