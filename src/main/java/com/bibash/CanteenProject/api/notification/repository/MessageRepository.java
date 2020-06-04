package com.bibash.CanteenProject.api.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bibash.CanteenProject.api.notification.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>,
    JpaSpecificationExecutor<Message> {

}
