package com.bibash.CanteenProject.api.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.Nullable;

import com.bibash.CanteenProject.core.enums.ItemStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String itemName;

    private String cookingTime;


    @Column(nullable = false)
    private Double price;


    private ItemStatus itemStatus;

}
