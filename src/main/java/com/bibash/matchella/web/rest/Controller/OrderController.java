package com.bibash.matchella.web.rest.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.matchella.api.OrderItem.ItemOrder;
import com.bibash.matchella.api.OrderItem.OrderDto;
import com.bibash.matchella.api.OrderItem.OrderDtoConverter;
import com.bibash.matchella.api.OrderItem.Service.OrderService;
import com.bibash.matchella.core.dto.RestResponseDto;
import com.bibash.matchella.core.dto.SearchDto;
import com.bibash.matchella.core.PaginationUtils;

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
     ItemOrder responseDto = orderService.orderItem(orderDto);
     if(responseDto == null){
         return new RestResponseDto().failureModel("Sorry your order is Invalid.");
     }
     orderService.save(responseDto);
    return new RestResponseDto().successModel(responseDto);
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<?> orderAction(@RequestBody OrderDto orderDto) {
        return new RestResponseDto().successModel(orderService.orderServingAction(orderDto));
    }

    @GetMapping("/pageable/{id}")
    public ResponseEntity<?> getPageableById(@PathVariable Long id) {
        return new RestResponseDto().successModel(orderService.findAllPageableById(id ,
            PaginationUtils.pageable(1, 5)));
    }
    @PostMapping("/history")
    public ResponseEntity<?> search(@RequestBody SearchDto searchDto, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new RestResponseDto().successModel(orderService.findBySearchObject(searchDto, PaginationUtils.pageable(page, size)));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getSumCount(@RequestParam String startDate , @RequestParam String endDate) {
        return new RestResponseDto().successModel(orderService.orderCount(new Date(startDate), new Date(endDate)));
    }
}
