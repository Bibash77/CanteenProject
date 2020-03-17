package com.bibash.CanteenProject.api.TopUpHistory.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bibash.CanteenProject.api.TopUpHistory.TopUpHistory;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.core.BaseService;

public interface TopUpHistoryService extends BaseService<TopUpHistory> {
public TopUpHistory saveHistoryFromWallet(Wallet wallet);

     Map topUpCount(Date startDate, Date endDate, Long id);

public List<TopUpHistory> getHistoryById(Long id);
}
