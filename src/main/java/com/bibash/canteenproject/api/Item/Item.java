package com.bibash.canteenproject.api.Item;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.canteenproject.core.enums.ItemStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String itemName;

    private String cookingTime;


    @Column(nullable = false)
    private Double price;


    private ItemStatus itemStatus;

    @Lob
    private String image;

}
