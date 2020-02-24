package com.bibash.CanteenProject.api.OrderItem.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;

public interface OrderRepository extends JpaRepository<ItemOrder , Long>,
    JpaSpecificationExecutor<ItemOrder> {

    Page<ItemOrder> findAllById(Long id, Pageable pageable);

    List<ItemOrder> findAllById(Long id);

    ItemOrder findAllByIdAndOrderCode(Long id , String orderCode);
}
