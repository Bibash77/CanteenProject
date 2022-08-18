package com.bibash.canteenproject.api.Item.service;

import java.util.List;

import com.bibash.canteenproject.api.itemvote.repository.ItemReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.api.Item.Repository.ItemRepository;

@Service("ItemService")
public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository;
    private final ItemReviewRepository itemReviewRepository;
    public ItemServiceImpl(ItemRepository itemRepository, ItemReviewRepository itemReviewRepository){
        this.itemRepository= itemRepository;
        this.itemReviewRepository = itemReviewRepository;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findOne(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Page<Item> findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List<Item> saveAll(List<Item> list) {
        return null;
    }
}
