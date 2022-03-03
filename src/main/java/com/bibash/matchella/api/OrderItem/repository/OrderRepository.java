package com.bibash.matchella.api.OrderItem.repository;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bibash.matchella.api.OrderItem.ItemOrder;

public interface OrderRepository extends JpaRepository<ItemOrder , Long>,
    JpaSpecificationExecutor<ItemOrder> {

    Page<ItemOrder> findAllById(Long id, Pageable pageable);

    List<ItemOrder> findAllById(Long id);

    ItemOrder findAllByIdAndOrderCode(Long id , String orderCode);

    /*@Query(value = "select count(i.quantity) as quantityCount, sum(i.expenditure) as expenditureCount from item_order i where CAST(i.created_at as DATE ) = CAST('2020-03-16' as DATE );", nativeQuery = true)
    Map<String , Integer> findSumOfOrder(Date date);
*/
    @Query("select count(i.quantity) as quantityCount , sum(i.expenditure) as expenditureCount from ItemOrder i where i.createdAt > :startDate AND i.createdAt < :endDate")
    Map<String , Integer> findSumOfOrder(Date startDate , Date endDate);
}
