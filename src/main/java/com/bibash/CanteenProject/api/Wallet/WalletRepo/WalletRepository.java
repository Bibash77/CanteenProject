package com.bibash.CanteenProject.api.Wallet.WalletRepo;
import java.util.Date;
import java.util.Map;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet , Long>,
    JpaSpecificationExecutor<Wallet> {

    public Wallet findWalletByUser(User user);

    @Query("select  sum(w.walletExpenditure) as walletExpenditure from Wallet w where w.createdAt > :startDate AND w.createdAt < :endDate")
    Map<String , Integer> findSumOfOrder(Date startDate , Date endDate);

    Wallet findByUserId(Long id);
}
