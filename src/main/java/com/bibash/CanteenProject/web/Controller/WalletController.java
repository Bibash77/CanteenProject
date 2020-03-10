package com.bibash.CanteenProject.web.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.TopUpHistory.Service.TopUpHistoryService;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;
import com.bibash.CanteenProject.core.PaginationUtils;

@RestController
@RequestMapping("v1/wallet")
public class WalletController {
    private WalletService walletService;
    private TopUpHistoryService topUpHistoryService;


    public WalletController(
        WalletService walletService,
        TopUpHistoryService topUpHistoryService) {
        this.walletService = walletService;
        this.topUpHistoryService = topUpHistoryService;
    }

    @PostMapping
    public ResponseEntity<?> saveWallet(@RequestBody Wallet wallet){
        return new RestResponseDto().successModel(walletService.save(wallet));
    }

    @PostMapping(value = "/top-up")
    public ResponseEntity<?> topUp(@RequestBody Wallet wallet){
        return new RestResponseDto().successModel(walletService.topUp(wallet));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllWallet(){
        return new RestResponseDto().successModel(walletService.findAll());
    }

    @GetMapping(value = "/history")
    public ResponseEntity<?> getTopUpHistory(){
        return new RestResponseDto().successModel(topUpHistoryService.findAll());
    }

    @GetMapping(value = "/history/{id}")
    public ResponseEntity<?> getTopUpHistory(@PathVariable Long id){
        return new RestResponseDto().successModel(topUpHistoryService.getHistoryById(id));
    }

    @PostMapping(value = "/list")
    public ResponseEntity<?> getAllByPagination(@RequestBody Object searchDto,
        @RequestParam("page") int page, @RequestParam("size") int size) {
        return new RestResponseDto()
            .successModel(walletService.findAllPageable(searchDto, PaginationUtils
                .pageable(page, size)));
    }
}
