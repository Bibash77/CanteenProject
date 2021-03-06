package com.bibash.CanteenProject.api.OrderItem.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.api.OrderItem.OrderDto;
import com.bibash.CanteenProject.core.BaseService;
import com.bibash.CanteenProject.core.BaseSpecBuilder;
import com.bibash.CanteenProject.core.Dto.SearchDto;

public interface OrderService extends BaseService<ItemOrder> {

     Page<ItemOrder> findAllPageableById(Long id , Pageable pageable);

    public ItemOrder orderItem(OrderDto orderDto);

     List<ItemOrder> findAllById(Long id);

    Page<ItemOrder> findBySearchObject(Object searchObj , Pageable pageable);

    ItemOrder orderServingAction(OrderDto orderDto);

    Map<String, Integer> orderCount(Date startDate , Date endDate);

    BaseSpecBuilder<ItemOrder> getSpec(Map<String, String> filterParams);
}
