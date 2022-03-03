package com.bibash.matchella.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bibash.matchella.api.User.Service.UserService;
import com.bibash.matchella.api.User.User;
import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.api.Wallet.WalletRepo.WalletRepository;
import com.bibash.matchella.web.rest.user.LoginDto;
import com.bibash.matchella.web.rest.user.UserDto;

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
