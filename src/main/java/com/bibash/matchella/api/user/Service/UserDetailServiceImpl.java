package com.bibash.matchella.api.user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bibash.matchella.api.user.repository.UserRepository;
import com.bibash.matchella.core.config.exception.CustomException;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
           return userRepository.findUserByUserName(username);
        }catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
    }
}
