package com.bibash.CanteenProject.api.TopUpHistory.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bibash.CanteenProject.api.TopUpHistory.TopUpHistory;

public interface TopUpRepo extends JpaRepository<TopUpHistory, Long> {

    List<TopUpHistory> findAllByUserId(Long id);

    @Query("select  sum(t.topUpAmount) as topUpAmount from TopUpHistory t where t.createdAt > :startDate AND t.createdAt < :endDate")
    Map<String , Integer> findSumOfOrder(Date startDate , Date endDate);

}
