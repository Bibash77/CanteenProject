package com.bibash.canteenproject.core.config;

import java.time.LocalDate;
import java.util.Date;
import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bibash.canteenproject.api.User.User;
import com.bibash.canteenproject.api.User.repository.UserRepository;
import com.bibash.canteenproject.api.Wallet.Wallet;
import com.bibash.canteenproject.api.Wallet.WalletRepo.WalletRepository;
import com.bibash.canteenproject.core.enums.AppConstant;
import com.bibash.canteenproject.core.enums.RoleType;
import com.bibash.canteenproject.core.enums.Status;

@Component
public class StartUpConfig {

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final PasswordEncoder passwordEncoder;

    public StartUpConfig(UserRepository userRepository,
        WalletRepository walletRepository,
        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
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
            final Wallet wallet = new Wallet();
            user.setFullName(AppConstant.DEFAULT_SUPER_ADMIN_NAME);
            user.setUserName(AppConstant.DEFAULT_SUPER_ADMIN_USERNAME);
            user.setPassword(passwordEncoder.encode(AppConstant.DEFAULT_SUPER_ADMIN_PASSWORD));
            user.setStatus(Status.ACTIVE);
            user.setBatch(String.valueOf(LocalDate.now().getYear()));
            user.setEmail("demo@gmail.com");
            user.setWalletAmount(0.0);
            user.setRoleType(RoleType.ADMIN);
            user.setUserCode("123456");

            wallet.setUser(userRepository.save(user));
            wallet.setCreatedAt(new Date());
            wallet.setWalletAmount(0.0);
            walletRepository.save(wallet);
        }
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
