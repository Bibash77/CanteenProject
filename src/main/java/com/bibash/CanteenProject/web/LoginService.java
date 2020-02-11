package com.bibash.CanteenProject.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import ch.qos.logback.core.pattern.ConverterUtil;
import com.bibash.CanteenProject.api.User.Service.UserService;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletRepo.WalletRepository;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.web.user.LoginDto;
import com.bibash.CanteenProject.web.user.UserDto;

@Component
public class LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private WalletRepository walletRepository;

    public UserDto loginChecking(LoginDto loginDto){
        User user = userService.findUserByName(loginDto.getUserName());
            if(!ObjectUtils.isEmpty(user)){
            Wallet wallet = walletRepository.findWalletByUser(user);
            UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user , userDto);
            if(user.getPassword().equals(loginDto.getPassword())){
                userDto.setWalletAmount(wallet.getWalletAmount());
                return userDto;
            }
        }
        return null;
    }
}
