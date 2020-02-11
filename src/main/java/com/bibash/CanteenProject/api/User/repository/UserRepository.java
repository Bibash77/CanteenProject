package com.bibash.CanteenProject.api.User.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibash.CanteenProject.api.User.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);
}
