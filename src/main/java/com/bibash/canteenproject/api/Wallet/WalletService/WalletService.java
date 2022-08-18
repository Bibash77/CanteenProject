package com.bibash.canteenproject.api.Wallet.WalletService;

import java.util.Date;
import java.util.Map;


import com.bibash.canteenproject.api.Wallet.Wallet;
import com.bibash.canteenproject.core.BaseService;

public interface WalletService extends BaseService<Wallet> {

     Wallet topUp(Wallet wallet);

    public Map topUpCount(Date startDate, Date endDate, Long id);

    Wallet deductAmountFromAccount(Long id , double deductAmount);

    Wallet getWalletByUser(Long id);


}
