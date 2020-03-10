package com.bibash.CanteenProject.api.OrderItem;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bibash.CanteenProject.api.Item.Item;
import com.bibash.CanteenProject.api.Item.service.ItemService;
import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.core.enums.CodeGeneratorUtils;
import com.bibash.CanteenProject.core.enums.OrderStatus;

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
            itemOrder.setOrderCode(codeGeneratorUtils.genOrderCode());
            itemOrder.setExpenditure(currentItem.getPrice() * orderDto.getQuantity());
            itemOrder.setItemName(orderDto.getItem().getItemName());
            itemOrder.setOrderStatus(OrderStatus.PENDING);
            itemOrder.getUser().setId(orderDto.getUserId());
            itemOrder.setCreatedAt(new Date());
        }
        Wallet wallet = deduct(orderDto.getUserId() , itemOrder.getExpenditure());
        return itemOrder;
    }

    private Wallet deduct(Long id , double deductAmount){
        Wallet wallet = new Wallet();
        User user = new User();
        user.setId(id);
        wallet.setUser(user);
        wallet.setDepositAmount(-deductAmount);
        return walletService.topUp(wallet);

    }

}
