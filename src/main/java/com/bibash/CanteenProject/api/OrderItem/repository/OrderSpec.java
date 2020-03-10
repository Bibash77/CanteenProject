package com.bibash.CanteenProject.api.OrderItem.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.core.enums.OrderStatus;

public class OrderSpec implements Specification<ItemOrder> {
    private static final String FILTER_BY_ORDER_STATUS = "orderStatus";
    private static final String FILTER_BY_CREATED_AT = "createdAt";
    private static final String     FILTER_BY_ITEM_NAME = "itemName";
    private static final String FILTER_BY_ORDER_CODE= "orderCode";
    private static final String FILTER_BY_USER_ID= "userId";
    private static final String FILTER_BY_DATE="date";
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
                    .or(criteriaBuilder
                        .equal(root.join("user").get("id"), Long.valueOf(value)));
            case FILTER_BY_ITEM_NAME:
            case FILTER_BY_ORDER_CODE:
                return criteriaBuilder.like(root.get(property), value + "%");
            case FILTER_BY_DATE:
                try {
                    Map dates = new ObjectMapper().readValue(value, Map.class);
                    return criteriaBuilder.between(root.get("createdAt"),
                        new SimpleDateFormat("MM/dd/yyyy")
                            .parse(String.valueOf(dates.get("startDate"))),
                        new SimpleDateFormat("MM/dd/yyyy")
                            .parse(String.valueOf(dates.get("endDate"))));
                } catch (JsonProcessingException | ParseException e) {
                    e.printStackTrace();
                }
            default:
                return null;
        }
    }
}
