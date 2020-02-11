package com.bibash.CanteenProject.api.User.Service;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.BaseService;

public interface UserService  extends BaseService<User> {

    public String userCodeGenerateor();

    public User changeStatus(User user);

    public User findUserByName(String userName);
}
