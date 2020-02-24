package com.bibash.CanteenProject.api.OrderItem.Service;

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

    public Page<ItemOrder> findAllPageableById(Long id , Pageable pageable);

    public List<ItemOrder> findAllById(Long id);

    List<ItemOrder> findBySearchObject(SearchDto searchObj);

    ItemOrder orderAction(OrderDto orderDto);

      public BaseSpecBuilder<ItemOrder> getSpec(Map<String, String> filterParams);
}
