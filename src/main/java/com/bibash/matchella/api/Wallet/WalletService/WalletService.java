package com.bibash.matchella.api.Wallet.WalletService;

import java.util.Date;
import java.util.Map;


import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.core.BaseService;

public interface WalletService extends BaseService<Wallet> {

     Wallet topUp(Wallet wallet);

    public Map topUpCount(Date startDate, Date endDate, Long id);

    Wallet deductAmountFromAccount(Long id , double deductAmount);

    Wallet getWalletByUser(Long id);


}
