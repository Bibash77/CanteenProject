package com.bibash.canteenproject.api.OrderItem;

import lombok.Data;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.core.enums.OrderStatus;

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
