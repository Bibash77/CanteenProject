package com.bibash.CanteenProject.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.core.Dto.RestResponseDto;
import com.bibash.CanteenProject.core.Dto.UserDto;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private UserService userService;
    private WalletService walletService;
    @Autowired
    public UserController(UserService userService,
    WalletService walletService){
        this.userService= userService;
        this.walletService= walletService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new RestResponseDto().successModel(userService.save(user));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAll(){
        return  new RestResponseDto().successModel(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new RestResponseDto().successModel(userService.findOne(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        Wallet wallet = new Wallet();
        wallet.setUser(userService.save(user));
        walletService.save(wallet);
        return new RestResponseDto().successModel(new User());
    }

    @PostMapping(value = "/status")
    public ResponseEntity<?> changeUserStatus(@RequestBody User user){
        return new RestResponseDto().successModel(userService.changeStatus(user));
    }

}
