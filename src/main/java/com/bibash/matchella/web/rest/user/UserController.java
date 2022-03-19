package com.bibash.matchella.web.rest.user;

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

import com.bibash.matchella.api.user.Service.UserService;
import com.bibash.matchella.api.user.User;
import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.api.Wallet.WalletService.WalletService;
import com.bibash.matchella.core.dto.RestResponseDto;
import com.bibash.matchella.core.enums.RoleType;
import com.bibash.matchella.core.enums.Status;
import com.bibash.matchella.web.LoginService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;
    private WalletService walletService;

    @Autowired
    private LoginService loginService;

    @Autowired
    public UserController(UserService userService,
        WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> getAuthenticated() {
        return new RestResponseDto().successModel(userService.getAuthenticated());
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new RestResponseDto().successModel(userService.save(user));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAll() {
        return new RestResponseDto().successModel(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new RestResponseDto().successModel(userService.findOne(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setStatus(Status.INACTIVE);
        user.setRoleType(RoleType.STUDENT);
        Wallet wallet = new Wallet();
        wallet.setUser(userService.save(user));
        walletService.save(wallet);
        return new RestResponseDto().successModel(new User());
    }

    @PostMapping(value = "/status")
    public ResponseEntity<?> changeUserStatus(@RequestBody User user) {
        return new RestResponseDto().successModel(userService.changeStatus(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        UserDto userDto = loginService.loginChecking(loginDto);
        if(userDto != null){
            return new RestResponseDto().successModel(userDto);
        }
       return new RestResponseDto().successModel("username or password invalid");
    }

    @GetMapping("/countUser")
    public ResponseEntity<?> countUser(@RequestParam String startDate , @RequestParam String endDate) {
        return new RestResponseDto().successModel(userService.countUser(new Date(startDate), new Date(endDate), Status.INACTIVE));
    }
}
