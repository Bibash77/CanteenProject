package com.bibash.matchella.api.OrderItem.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bibash.matchella.api.Item.Item;
import com.bibash.matchella.api.Item.service.ItemService;
import com.bibash.matchella.api.OrderItem.ItemOrder;
import com.bibash.matchella.api.OrderItem.OrderDto;
import com.bibash.matchella.api.OrderItem.repository.OrderRepository;
import com.bibash.matchella.api.OrderItem.repository.OrderSpecBuilder;
import com.bibash.matchella.api.User.Service.UserService;
import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.api.Wallet.WalletService.WalletService;
import com.bibash.matchella.core.config.exception.CustomException;
import com.bibash.matchella.core.enums.AppConstant;
import com.bibash.matchella.core.enums.CodeGeneratorUtils;
import com.bibash.matchella.core.enums.OrderStatus;
import com.bibash.matchella.core.enums.Status;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private WalletService walletService;
    private ItemService itemService;
    private UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();


    public OrderServiceImpl(
        OrderRepository orderRepository,
        WalletService walletService,
        ItemService itemService, UserService userService) {
        this.orderRepository = orderRepository;
        this.walletService = walletService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public List<ItemOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public ItemOrder findOne(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public ItemOrder save(ItemOrder itemOrder) {
        return orderRepository.save(itemOrder);
    }

    @Override
    public Page<ItemOrder> findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List<ItemOrder> saveAll(List<ItemOrder> list) {
        return null;
    }

    @Override
    public Page<ItemOrder> findAllPageableById(Long id , Pageable pageable) {
        return orderRepository.findAllById(id , pageable);
    }

    @Override
    public ItemOrder orderItem(OrderDto orderDto) {
        Wallet wallet = walletService.getWalletByUser(orderDto.getUserId());
        Item currentItem = itemService.findOne(orderDto.getItem().getId());
        if(this.checkOrderEligible(wallet.getWalletAmount() , currentItem.getPrice()).equals(AppConstant.ORDER_ELIGIBLE)){
            ItemOrder itemOrder = new ItemOrder();
            if(!ObjectUtils.isEmpty(wallet.getUser().getId()) && wallet.getUser().getStatus() == Status.ACTIVE) {
                BeanUtils.copyProperties(orderDto, itemOrder);

                itemOrder.setOrderCode(CodeGeneratorUtils.genOrderCode());
                itemOrder.setExpenditure(currentItem.getPrice() * orderDto.getQuantity());
                itemOrder.setItemName(orderDto.getItem().getItemName());
                itemOrder.setOrderStatus(OrderStatus.PENDING);
                itemOrder.getUser().setId(orderDto.getUserId());
                itemOrder.setCreatedAt(new Date());
            }
            walletService.deductAmountFromAccount(orderDto.getUserId() , itemOrder.getExpenditure());
            return itemOrder;
        } else {
                throw new CustomException("You do not have enough balance to make this order !!");
        }
    }

    @Override
    public List<ItemOrder> findAllById(Long id) {
        return orderRepository.findAllById(id);
    }

    @Override
    public Page<ItemOrder> findBySearchObject(Object searchObj , Pageable pageable) {
        Map<String, String> s = objectMapper.convertValue(searchObj, Map.class);
        s.values().removeIf(Objects::isNull);
        final OrderSpecBuilder orderSpecBuilder = new OrderSpecBuilder(s);
        final Specification<ItemOrder> specification = orderSpecBuilder.build();
        return orderRepository.findAll(specification , pageable);
    }

    @Override
    public ItemOrder orderServingAction(OrderDto orderDto) {
        ItemOrder itemOrder = orderRepository.findAllByIdAndOrderCode(orderDto.getId() , orderDto.getOrderCode());
        if(!ObjectUtils.isEmpty(itemOrder)){
            itemOrder.setOrderStatus(orderDto.getOrderStatus());
            return  orderRepository.save(itemOrder);
        }
        else
       throw new CustomException("Unable to save your action");
    }

    @Override
    public Map<String , Integer> orderCount(Date startDate , Date endDate) {
       return  orderRepository.findSumOfOrder(startDate , endDate);
    }

    @Override
    public OrderSpecBuilder getSpec(Map<String, String> filterParams) {
        return new OrderSpecBuilder(filterParams);
    }

    String checkOrderEligible(Double userBalance , Double itemPrice){
        if (userBalance >= itemPrice){
            return AppConstant.ORDER_ELIGIBLE;
        }
       throw new CustomException("your balance Rs." + userBalance + " is not sufficient to make this order");
    }
}
