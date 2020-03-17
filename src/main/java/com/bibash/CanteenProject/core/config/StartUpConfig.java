package com.bibash.CanteenProject.core.config;

import java.util.Date;
import javax.annotation.PostConstruct;

import org.hibernate.usertype.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.User.repository.UserRepository;
import com.bibash.CanteenProject.core.enums.AppConstant;
import com.bibash.CanteenProject.core.enums.RoleType;
import com.bibash.CanteenProject.core.enums.Status;

@Component
public class StartUpConfig {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public StartUpConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void populate() {
        createSuperAdmin();
    }

    private void createSuperAdmin() {
        final User admin = userRepository.findUserByUserName(AppConstant.DEFAULT_SUPER_ADMIN_USERNAME);
        if (admin == null) {
            final User user = new User();
            user.setFullName(AppConstant.DEFAULT_SUPER_ADMIN_NAME);
            user.setUserName(AppConstant.DEFAULT_SUPER_ADMIN_USERNAME);
            user.setPassword(passwordEncoder.encode(AppConstant.DEFAULT_SUPER_ADMIN_PASSWORD));
            user.setStatus(Status.ACTIVE);
            user.setEmail("demo@gmail.com");
            user.setWalletAmount(0.0);
            user.setRoleType(RoleType.ADMIN);
            user.setUserCode("123456");
            userRepository.save(user);
        }
    }

}
