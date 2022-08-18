package com.bibash.canteenproject.api.Item.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibash.canteenproject.api.Item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
