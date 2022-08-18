package com.bibash.canteenproject.api.itemvote.service;

import com.bibash.canteenproject.api.itemvote.ItemReview;
import com.bibash.canteenproject.api.itemvote.repository.ItemReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemReviewServiceImpl implements ItemReviewService{

    private final ItemReviewRepository itemReviewRepository;

    public ItemReviewServiceImpl(ItemReviewRepository itemReviewRepository) {
        this.itemReviewRepository = itemReviewRepository;
    }

    @Override
    public List<ItemReview> findAll() {
        return itemReviewRepository.findAll();
    }

    @Override
    public ItemReview findOne(Long id) {
        return itemReviewRepository.getOne(id);
    }

    @Override
    public ItemReview save(ItemReview itemReview) {
        return itemReviewRepository.save(itemReview);
    }

    @Override
    public Page<ItemReview> findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List<ItemReview> saveAll(List<ItemReview> list) {
        return null;
    }
}
