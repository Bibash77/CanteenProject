package com.bibash.canteenproject.api.itemvote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_review")
public class ItemReview {

    @Id
    @GeneratedValue
    private Long id;

    private Integer rating;

    private String detail;
}
