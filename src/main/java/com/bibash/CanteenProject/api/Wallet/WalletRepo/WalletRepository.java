package com.bibash.CanteenProject.api.Wallet.WalletRepo;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet , Long> {

    public Wallet findWalletByUser(User user);
}
