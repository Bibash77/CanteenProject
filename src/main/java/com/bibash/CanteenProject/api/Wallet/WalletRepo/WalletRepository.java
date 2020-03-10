package com.bibash.CanteenProject.api.Wallet.WalletRepo;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WalletRepository extends JpaRepository<Wallet , Long>,
    JpaSpecificationExecutor<Wallet> {

    public Wallet findWalletByUser(User user);
}
