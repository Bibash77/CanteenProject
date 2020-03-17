package com.bibash.CanteenProject.api.User.repository;

import java.util.Date;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.enums.Status;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);

    Long countByStatus(Status status);

    /*@Query("select COUNT(*) from User u where u.status= :status")
    Map<String , Integer> countUsers(Status status);*/

}
