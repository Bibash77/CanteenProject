package com.bibash.matchella.api.Item.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bibash.matchella.api.Item.Item;
import com.bibash.matchella.api.Item.Repository.ItemRepository;

@Service("ItemService")
public class ItemServiceImpl implements ItemService{
    private ItemRepository itemRepository;
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository= itemRepository;
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
