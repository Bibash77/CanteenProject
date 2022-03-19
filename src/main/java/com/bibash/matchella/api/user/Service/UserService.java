package com.bibash.matchella.api.user.Service;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bibash.matchella.api.user.User;
import com.bibash.matchella.core.BaseService;
import com.bibash.matchella.core.enums.Status;

public interface UserService  extends BaseService<User> , UserDetailsService {

    public String userCodeGenerateor();

    public User changeStatus(User user);

    public User findUserByName(String userName);

    Map<String , Long> countUser(Date startDate, Date endDate, Status status);

    public User findById(Long userId);

    User getAuthenticated();
}
