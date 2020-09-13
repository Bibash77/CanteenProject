package com.bibash.CanteenProject.api.OrderItem;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;

import com.bibash.CanteenProject.api.Item.Item;
import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.BaseEntity;
import com.bibash.CanteenProject.core.enums.OrderStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_order")
public class ItemOrder {
    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @OneToOne
    private Item item;

    private String itemName;

    private Double itemPrice;

    private String orderCode;

    private Double expenditure;

    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user = new User();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt = new Date();

}
