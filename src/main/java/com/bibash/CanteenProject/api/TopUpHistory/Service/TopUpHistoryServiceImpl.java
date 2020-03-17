package com.bibash.CanteenProject.api.TopUpHistory.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bibash.CanteenProject.api.TopUpHistory.TopUpHistory;
import com.bibash.CanteenProject.api.TopUpHistory.repository.TopUpRepo;
import com.bibash.CanteenProject.api.Wallet.Wallet;

@Service
public class TopUpHistoryServiceImpl implements TopUpHistoryService{

    private final TopUpRepo topUpRepo;

    public TopUpHistoryServiceImpl(
        TopUpRepo topUpRepo) {
        this.topUpRepo = topUpRepo;
    }

    @Override
    public List<TopUpHistory> findAll() {
        return topUpRepo.findAll();
    }

    @Override
    public TopUpHistory findOne(Long id) {
        return null;
    }

    @Override
    public TopUpHistory save(TopUpHistory topUpHistory) {
        return topUpRepo.save(topUpHistory);
    }

    @Override
    public Page<TopUpHistory> findAllPageable(Object t, Pageable pageable) {
        return topUpRepo.findAll(pageable);
    }

    @Override
    public List<TopUpHistory> saveAll(List<TopUpHistory> list) {
        return null;
    }

    @Override
    public TopUpHistory saveHistoryFromWallet(Wallet wallet) {
        TopUpHistory topUpHistory = new TopUpHistory();
        topUpHistory.setCreatedAt(new Date());
        topUpHistory.setTopUpAmount(wallet.getDepositAmount());
        topUpHistory.setUserId(wallet.getUser().getId());
        topUpHistory.setRemainingAmount(wallet.getWalletAmount());
        return save(topUpHistory);
    }

    @Override
    public List<TopUpHistory> getHistoryById(Long id) {
        return topUpRepo.findAllByUserId(id);
    }

    @Override
    public Map topUpCount(Date startDate, Date endDate, Long id) {
        return topUpRepo.findSumOfOrder(startDate , endDate);
    }
}
