package com.bibash.CanteenProject.web.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;

@RestController
@RequestMapping("v1/wallet")
public class WalletController {
    private WalletService walletService;


    public WalletController(
        WalletService walletService) {
        this.walletService = walletService;
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
}
