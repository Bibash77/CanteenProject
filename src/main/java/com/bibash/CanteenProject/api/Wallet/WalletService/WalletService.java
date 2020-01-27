package com.bibash.CanteenProject.api.Wallet.WalletService;

import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.core.BaseService;

public interface WalletService extends BaseService<Wallet> {

    public Wallet topUp(Wallet wallet);

}
