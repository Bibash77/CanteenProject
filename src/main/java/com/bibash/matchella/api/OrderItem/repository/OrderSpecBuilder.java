package com.bibash.matchella.api.OrderItem.repository;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.matchella.api.OrderItem.ItemOrder;
import com.bibash.matchella.core.BaseSpecBuilder;

public class OrderSpecBuilder extends BaseSpecBuilder<ItemOrder> {

    public OrderSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<ItemOrder> getSpecification(String property, String filterValue) {
        return new OrderSpec(property , filterValue);
    }
}
