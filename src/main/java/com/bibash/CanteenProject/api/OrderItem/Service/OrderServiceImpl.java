package com.bibash.CanteenProject.api.OrderItem.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.api.OrderItem.OrderDto;
import com.bibash.CanteenProject.api.OrderItem.repository.OrderRepository;
import com.bibash.CanteenProject.api.OrderItem.repository.OrderSpecBuilder;
import com.bibash.CanteenProject.core.Dto.SearchDto;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ObjectMapper objectMapper = new ObjectMapper();


    public OrderServiceImpl(
        OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ItemOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public ItemOrder findOne(Long id) {
        return null;
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
    public List<ItemOrder> findAllById(Long id) {
        return orderRepository.findAllById(id);
    }

    @Override
    public Page<ItemOrder> findBySearchObject(SearchDto searchObj , Pageable pageable) {
        Map<String, String> s = objectMapper.convertValue(searchObj, Map.class);
        s.values().removeIf(Objects::isNull);
        final OrderSpecBuilder orderSpecBuilder = new OrderSpecBuilder(s);
        final Specification<ItemOrder> specification = orderSpecBuilder.build();
        return orderRepository.findAll(specification , pageable);
    }

    @Override
    public ItemOrder orderAction(OrderDto orderDto) {
        ItemOrder itemOrder = orderRepository.findAllByIdAndOrderCode(orderDto.getId() , orderDto.getOrderCode());
        if(!ObjectUtils.isEmpty(itemOrder)){
            itemOrder.setOrderStatus(orderDto.getOrderStatus());
            return  orderRepository.save(itemOrder);
        }
        return null;
    }

    @Override
    public Map<String , Integer> orderCount(Date startDate , Date endDate) {
       return  orderRepository.findSumOfOrder(startDate , endDate);
    }

    @Override
    public OrderSpecBuilder getSpec(Map<String, String> filterParams) {
        return new OrderSpecBuilder(filterParams);
    }
}
