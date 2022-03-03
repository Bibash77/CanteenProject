package com.bibash.matchella.api.Item.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibash.matchella.api.Item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
