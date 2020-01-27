package com.bibash.CanteenProject.api.Item.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibash.CanteenProject.api.Item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
