package com.bibash.canteenproject.api.TopUpHistory.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bibash.canteenproject.api.TopUpHistory.TopUpHistory;
import com.bibash.canteenproject.api.Wallet.Wallet;
import com.bibash.canteenproject.core.BaseService;

public interface TopUpHistoryService extends BaseService<TopUpHistory> {

    TopUpHistory saveHistoryFromWallet(Wallet wallet);

    Map topUpCount(Date startDate, Date endDate, Long id);

    List<TopUpHistory> getHistoryById(Long id);

    Page<TopUpHistory> findBySearchObject(Object searchObj, Pageable pageable);
}
