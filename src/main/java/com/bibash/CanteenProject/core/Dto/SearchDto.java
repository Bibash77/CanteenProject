package com.bibash.CanteenProject.core.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.core.enums.ItemStatus;
import com.bibash.CanteenProject.core.enums.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

   private String userId;

   private String createdAt;

   private OrderStatus orderStatus;

   private String orderCode;

   private String itemName;
}
