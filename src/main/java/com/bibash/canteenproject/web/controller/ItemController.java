package com.bibash.canteenproject.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.api.Item.service.ItemService;
import com.bibash.canteenproject.core.Dto.RestResponseDto;

@RestController
@RequestMapping("v1/item")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        return new RestResponseDto().successModel(itemService.save(item));
    }
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllItem(){
        return new RestResponseDto().successModel(itemService.findAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new RestResponseDto().successModel(itemService.findOne(id));
    }

}
