package com.bibash.matchella.web.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.matchella.api.TopUpHistory.Service.TopUpHistoryService;
import com.bibash.matchella.core.dto.RestResponseDto;
import com.bibash.matchella.core.PaginationUtils;

@RestController
@RequestMapping("v1/top-up-history")
public class TopUpHistoryController {

    private TopUpHistoryService topUpHistoryService;

    private TopUpHistoryController(
        TopUpHistoryService topUpHistoryService){
        this.topUpHistoryService = topUpHistoryService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchTopUpHistory(@RequestBody Object searchDto, @RequestParam("page") int page, @RequestParam("size") int size){
        return new RestResponseDto().successModel(topUpHistoryService.findBySearchObject(searchDto , PaginationUtils.pageable(page, size)));
    }
}
