package com.bibash.CanteenProject.api.OrderItem.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.api.OrderItem.repository.OrderRepository;
import com.bibash.CanteenProject.api.OrderItem.repository.OrderSpecBuilder;
import com.bibash.CanteenProject.core.Dto.SearchDto;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<ItemOrder> findBySearchObject(SearchDto searchObj) {
        Map<String, String> s = objectMapper.convertValue(searchObj, Map.class);
        s.values().removeIf(Objects::isNull);
        final OrderSpecBuilder orderSpecBuilder = new OrderSpecBuilder(s);
        final Specification<ItemOrder> specification = orderSpecBuilder.build();
        return orderRepository.findAll(specification);
/*        List<ItemOrder> itemOrders = new ArrayList<>();
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();

        Session session = entityManager.unwrap(Session.class);
        String queryString = "select i from  ItemOrder   i where i.orderStatus=:orderStatus";

        if (searchObj.getId() != null) {
            queryString += " and i.user.id=:userId";
        }
        if (searchObj.getCreatedAt() != null) {
            queryString += " and i.getCreatedAt=:createdAt";
        }
        Query query = session.createQuery(queryString);
        if(!ObjectUtils.isEmpty(searchObj.getOrderStatus())){
            query.setParameter("orderStatus" , searchObj.getOrderStatus());
        }
        if(!ObjectUtils.isEmpty(searchObj.getId())){
            query.setParameter("userId" , searchObj.getId());
        }
        itemOrders.addAll(query.list());
        return itemOrders;*/
    }
    @Override
    public OrderSpecBuilder getSpec(Map<String, String> filterParams) {
        return new OrderSpecBuilder(filterParams);
    }
}
