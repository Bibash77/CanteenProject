package com.bibash.CanteenProject.api.Wallet.WalletService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.TopUpHistory.Service.TopUpHistoryService;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletRepo.WalletRepository;

@Service("walletService")
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final TopUpHistoryService topUpHistoryService;

    public WalletServiceImpl(
        WalletRepository walletRepository,
        TopUpHistoryService topUpHistoryService) {
        this.walletRepository = walletRepository;
        this.topUpHistoryService = topUpHistoryService;
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findOne(Long id) {
        return walletRepository.getOne(id);
    }

    @Override
    public Page<Wallet> findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List<Wallet> saveAll(List<Wallet> list) {
        return walletRepository.saveAll(list);
    }

    @Override
    public Wallet save(Wallet wallet) {
        if(wallet.getId() == null) {
            wallet.setWalletExpenditure(0.0);
            wallet.setWalletAmount(0.0);
        }
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet topUp(Wallet wallet) {
        Wallet userWallet = walletRepository.findWalletByUser(wallet.getUser());
        userWallet.setWalletAmount(userWallet.getWalletAmount() + wallet.getDepositAmount());
        userWallet.setDepositAmount(wallet.getDepositAmount());
        topUpHistoryService.saveHistoryFromWallet(userWallet);
        return walletRepository.save(userWallet);
    }
}
