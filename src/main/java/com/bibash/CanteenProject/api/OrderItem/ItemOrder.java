package com.bibash.CanteenProject.api.OrderItem;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.api.Item.Item;
import com.bibash.CanteenProject.core.BaseEntity;
import com.bibash.CanteenProject.core.enums.OrderStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_order")
public class ItemOrder extends BaseEntity {
    @Id
    @GeneratedValue
    private int id;

    private int quantity;

    @Column(nullable = false)
    private BigInteger orderCode;

    @OneToOne
    private Item item;

    private OrderStatus orderStatus;


}
