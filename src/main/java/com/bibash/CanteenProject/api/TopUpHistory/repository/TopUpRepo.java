package com.bibash.CanteenProject.api.TopUpHistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibash.CanteenProject.api.TopUpHistory.TopUpHistory;

public interface TopUpRepo extends JpaRepository<TopUpHistory, Long> {

    List<TopUpHistory> findAllByUserId(Long id);
}
