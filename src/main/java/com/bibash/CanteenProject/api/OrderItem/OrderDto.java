package com.bibash.CanteenProject.api.OrderItem;

import lombok.Data;

import com.bibash.CanteenProject.api.Item.Item;
import com.bibash.CanteenProject.core.enums.OrderStatus;

@Data
public class OrderDto {
    private Long id;

    private Long userId;

    private int quantity;

    private String orderCode;

    private Item item;

    private OrderStatus orderStatus;

    private Double expenditure;

}
