package com.bibash.matchella.api.TopUpHistory.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bibash.matchella.api.TopUpHistory.TopUpHistory;
import com.bibash.matchella.api.TopUpHistory.TopUpHistorySpecBuilder;
import com.bibash.matchella.api.TopUpHistory.repository.TopUpRepo;
import com.bibash.matchella.api.Wallet.Wallet;
import com.bibash.matchella.core.enums.TransactionType;

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
        if (wallet.getDepositAmount() > 0) {
            topUpHistory.setTransactionType(TransactionType.TOPUP);
        } else {
            topUpHistory.setTransactionType(TransactionType.ORDER);
        }
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
    public Page<TopUpHistory> findBySearchObject(Object searchObj, Pageable pageable) {
        Map<String, String> s = new ObjectMapper().convertValue(searchObj, Map.class);
        s.values().removeIf(Objects::isNull);
        final TopUpHistorySpecBuilder topUpHistorySpecBuilder = new TopUpHistorySpecBuilder(s);
        final Specification<TopUpHistory> specification = topUpHistorySpecBuilder.build();
        return topUpRepo.findAll(specification , pageable);
    }

    @Override
    public Map topUpCount(Date startDate, Date endDate, Long id) {
        return topUpRepo.findSumOfOrder(startDate , endDate, TransactionType.TOPUP);
    }
}
