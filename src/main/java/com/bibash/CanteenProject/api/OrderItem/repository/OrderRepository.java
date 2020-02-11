package com.bibash.CanteenProject.api.OrderItem.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bibash.CanteenProject.api.Item.Item;
import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.core.Dto.SearchDto;

public interface OrderRepository extends JpaRepository<ItemOrder , Long>,
    JpaSpecificationExecutor<ItemOrder> {

    Page<ItemOrder> findAllById(Long id, Pageable pageable);

    List<ItemOrder> findAllById(Long id);

    @Query(value = "select i from ItemOrder i where i.user.id like concat(:id,'%')"
        + " order by i.orderStatus desc ")
    List<ItemOrder> search(Long id);
}
