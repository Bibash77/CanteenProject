package com.bibash.canteenproject.api.TopUpHistory.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bibash.canteenproject.api.TopUpHistory.TopUpHistory;
import com.bibash.canteenproject.core.enums.TransactionType;

public interface TopUpRepo extends JpaRepository<TopUpHistory, Long>,
    JpaSpecificationExecutor<TopUpHistory> {

    List<TopUpHistory> findAllByUserId(Long id);

    @Query("select  sum(t.topUpAmount) as topUpAmount from TopUpHistory t where  t.transactionType = :transactionType And t.createdAt > :startDate AND t.createdAt < :endDate")
    Map<String , Integer> findSumOfOrder(Date startDate , Date endDate, TransactionType transactionType);

    @Query("select t from TopUpHistory t where  t.transactionType = :transactionType And t.createdAt > :startDate AND t.createdAt < :endDate")
    List<TopUpHistory> findTopUpHistory(Date startDate , Date endDate, TransactionType transactionType);

}
