package com.bibash.CanteenProject.api.OrderItem.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.core.enums.OrderStatus;

public class OrderSpec implements Specification<ItemOrder> {
    private static final String FILTER_BY_ORDER_STATUS = "orderStatus";
    private static final String FILTER_BY_CREATED_AT = "createdAt";
    private static final String FILTER_BY_ITEM_NAME = "itemName";
    private static final String FILTER_BY_ORDER_CODE= "orderCode";
    private static final String FILTER_BY_USER_ID= "userId";
    private final String property;
    private final String value;

    public  OrderSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<ItemOrder> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {

            case FILTER_BY_ORDER_STATUS:
                return criteriaBuilder.equal(root.get(property), OrderStatus.valueOf(value));
            case FILTER_BY_USER_ID:
                return criteriaBuilder
                    .and(criteriaBuilder
                        .equal(root.join("user").get("id"), Long.valueOf(value)));
            default:
                return null;
        }
    }
}
