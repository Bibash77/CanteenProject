package com.bibash.CanteenProject.api.User.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.User.repository.UserRepository;
import com.bibash.CanteenProject.api.Wallet.WalletService.WalletService;
import com.bibash.CanteenProject.core.enums.Status;

@Service()
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final WalletService walletService;

    public UserServiceImpl(UserRepository userRepository,
        WalletService walletService){
        this.userRepository= userRepository;
        this.walletService = walletService;
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
        if(user.getId() == null){
            user.setWalletAmount(0.0);
            user.setUserCode(userCodeGenerateor());
            user.setStatus(Status.INACTIVE);
        }

        return userRepository.save(user);
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
        String userCode = String.valueOf(new Random().nextInt(1000)).concat(String.valueOf(new Date().getDate()) + String.valueOf(new Date().getSeconds()));
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
        Map<String , Long> map = new HashMap<>();
        map.put("user", userRepository.countByStatus(status));
        return map;
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }
}
