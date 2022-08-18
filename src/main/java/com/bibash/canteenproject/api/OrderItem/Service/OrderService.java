package com.bibash.canteenproject.api.OrderItem.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bibash.canteenproject.api.OrderItem.ItemOrder;
import com.bibash.canteenproject.api.OrderItem.OrderDto;
import com.bibash.canteenproject.core.BaseService;
import com.bibash.canteenproject.core.BaseSpecBuilder;

public interface OrderService extends BaseService<ItemOrder> {

     Page<ItemOrder> findAllPageableById(Long id , Pageable pageable);

    public ItemOrder orderItem(OrderDto orderDto);

     List<ItemOrder> findAllById(Long id);

    Page<ItemOrder> findBySearchObject(Object searchObj , Pageable pageable);

    ItemOrder orderServingAction(OrderDto orderDto);

    Map<String, Integer> orderCount(Date startDate , Date endDate);

    BaseSpecBuilder<ItemOrder> getSpec(Map<String, String> filterParams);
}
