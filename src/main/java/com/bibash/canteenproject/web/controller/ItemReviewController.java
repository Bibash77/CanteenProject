package com.bibash.canteenproject.web.controller;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.api.itemvote.ItemReview;
import com.bibash.canteenproject.api.itemvote.service.ItemReviewService;
import com.bibash.canteenproject.core.Dto.RestResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/item")
public class ItemReviewController {

    private final ItemReviewService itemService;

    public ItemReviewController(ItemReviewService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ItemReview itemReview) {
        return new RestResponseDto().successModel(itemService.save(itemReview));
    }
}
