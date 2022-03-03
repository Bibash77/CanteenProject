package com.bibash.matchella.api.User.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bibash.matchella.api.User.User;
import com.bibash.matchella.api.User.repository.UserRepository;
import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.api.Wallet.WalletService.WalletService;
import com.bibash.matchella.core.config.exception.CustomException;
import com.bibash.matchella.core.enums.Status;

@Service("userDetailService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
        WalletService walletService,
        PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.walletService = walletService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        try {
            if (user.getId() == null) {
                user.setWalletAmount(0.0);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setUserCode(userCodeGenerateor());
                user.setStatus(Status.INACTIVE);
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new CustomException(
                "User with username: " + user.getUsername() + " already exist!!!");
        }
    }

    @Override
    public Page findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List saveAll(List list) {
        return null;
    }

    @Override
    public String userCodeGenerateor() {
        String userCode = String.valueOf(new Random().nextInt(1000)).concat(new Date().getDate() + String.valueOf(new Date().getSeconds()));
        return userCode;
    }

    @Override
    public User changeStatus(User user) {
        User user1 = userRepository.getOne(user.getId());
        user1.setStatus(user.getStatus());
        user1.setRoleType(user.getRoleType());

        return userRepository.save(user1);
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public Map<String , Long> countUser(Date startDate, Date endDate, Status status) {
        return userRepository.countUsers(status);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public User getAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof UserDetails){
            User user = (User) authentication.getPrincipal();
            user = this.findUserByName(user.getUsername());
            Wallet wallet = walletService.getWalletByUser(user.getId());
            user.setWalletAmount(wallet.getWalletAmount());
            return user;
        } else {
            throw new UsernameNotFoundException("User is not Authenticated; Found type: " + authentication.getPrincipal().getClass());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username);
    }
}
