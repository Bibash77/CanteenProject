package com.bibash.CanteenProject.api.User.Service;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.BaseService;
import com.bibash.CanteenProject.core.enums.Status;

public interface UserService  extends BaseService<User> , UserDetailsService {

    public String userCodeGenerateor();

    public User changeStatus(User user);

    public User findUserByName(String userName);

    Map<String , Long> countUser(Date startDate, Date endDate, Status status);

    public User findById(Long userId);

    User getAuthenticated();
}
