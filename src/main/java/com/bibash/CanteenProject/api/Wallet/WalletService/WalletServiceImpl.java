package com.bibash.CanteenProject.api.Wallet.WalletService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.api.Wallet.WalletRepo.WalletRepository;
import com.bibash.CanteenProject.core.enums.Status;

@Service("walletService")
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;

    public WalletServiceImpl(
        WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
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
        Wallet userWallet = new Wallet();
        if(wallet.getId() == null){
            userWallet.setWalletExpenditure(0.0);
            userWallet.setWalletAmount(0.0);
        }else {
            userWallet = topUp(wallet);
        }
        return walletRepository.save(userWallet);
    }

    @Override
    public Wallet topUp(Wallet wallet) {
        Wallet userWallet = walletRepository.findWalletByUser(wallet.getUser());
        userWallet.getUser().setWalletAmount(userWallet.getWalletAmount() + wallet.getDepositAmount());
        userWallet.setWalletAmount(userWallet.getWalletAmount() + wallet.getDepositAmount());
        return walletRepository.save(userWallet);
    }
}
