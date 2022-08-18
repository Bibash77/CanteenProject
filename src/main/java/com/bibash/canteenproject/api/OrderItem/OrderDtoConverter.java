package com.bibash.canteenproject.api.OrderItem;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.api.Item.service.ItemService;
import com.bibash.canteenproject.api.User.Service.UserService;
import com.bibash.canteenproject.api.Wallet.Wallet;
import com.bibash.canteenproject.api.Wallet.WalletService.WalletService;
import com.bibash.canteenproject.core.enums.CodeGeneratorUtils;
import com.bibash.canteenproject.core.enums.OrderStatus;

@Component
public class OrderDtoConverter {


    private CodeGeneratorUtils  codeGeneratorUtils;

    private WalletService walletService;

    private UserService userService;

    private ItemService itemService;

    public OrderDtoConverter(
        CodeGeneratorUtils codeGeneratorUtils,
        WalletService walletService,
        UserService userService, ItemService itemService) {
        this.codeGeneratorUtils = codeGeneratorUtils;
        this.walletService = walletService;
        this.userService = userService;
        this.itemService = itemService;
    }

    public ItemOrder order(OrderDto orderDto){/*
        User user = userService.findOne(orderDto.getUserId());*/
        ItemOrder itemOrder = new ItemOrder();
        if(!ObjectUtils.isEmpty(orderDto.getUserId())/* && user.getStatus() != Status.INACTIVE*/) {
            Item currentItem = itemService.findOne(orderDto.getItem().getId());
            BeanUtils.copyProperties(orderDto, itemOrder);

            itemOrder.setOrderCode(CodeGeneratorUtils.genOrderCode());
            itemOrder.setExpenditure(currentItem.getPrice() * orderDto.getQuantity());
            itemOrder.setItemPrice(currentItem.getPrice());
            itemOrder.setItemName(orderDto.getItem().getItemName());
            itemOrder.setOrderStatus(OrderStatus.PENDING);
            itemOrder.getUser().setId(orderDto.getUserId());
            itemOrder.setCreatedAt(new Date());
        }
        Wallet wallet = walletService.deductAmountFromAccount(orderDto.getUserId() , itemOrder.getExpenditure());
        return itemOrder;
    }
}
