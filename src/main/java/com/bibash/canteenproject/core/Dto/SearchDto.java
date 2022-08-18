package com.bibash.canteenproject.core.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.canteenproject.core.enums.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

   private String userId;

   private String date;

   private OrderStatus orderStatus;

   private String orderCode;

   private String itemName;
}
