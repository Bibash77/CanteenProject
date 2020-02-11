package com.bibash.CanteenProject.web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.api.OrderItem.OrderDto;
import com.bibash.CanteenProject.api.OrderItem.OrderDtoConverter;
import com.bibash.CanteenProject.api.OrderItem.Service.OrderService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;
import com.bibash.CanteenProject.core.Dto.SearchDto;
import com.bibash.CanteenProject.core.PaginationUtils;

@RequestMapping("v1/order")
@RestController
public class OrderController {

    private OrderService orderService;
    private OrderDtoConverter orderDtoConverter;

    @Autowired
    public OrderController(
        OrderService orderService,
        OrderDtoConverter orderDtoConverter) {
        this.orderService = orderService;
        this.orderDtoConverter = orderDtoConverter;
    }

    @PostMapping
    public ResponseEntity<?> orderItem(@RequestBody OrderDto orderDto) {
    return new RestResponseDto().successModel(orderService.save(orderDtoConverter.order(orderDto)));
    }

    @GetMapping("/pageable/{id}")
    public ResponseEntity<?> getPageableById(@PathVariable Long id) {
        return new RestResponseDto().successModel(orderService.findAllPageableById(id ,
            PaginationUtils.pageable(1, 5)));
    }
    @PostMapping("/history")
    public ResponseEntity<?> search(@RequestBody SearchDto searchDto) {
        List<ItemOrder> itemOrderList;
        itemOrderList = orderService.findBySearchObject(searchDto);
        return new RestResponseDto().successModel(itemOrderList);
    }
}
