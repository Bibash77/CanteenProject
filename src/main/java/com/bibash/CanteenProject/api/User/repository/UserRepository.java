package com.bibash.CanteenProject.api.User.repository;

import java.util.Date;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.enums.Status;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);


    Long countByStatus(Status status);

    @Query("select COUNT(u) as user from User u where u.status= :status")
    Map<String , Long> countUsers(Status status);
}
