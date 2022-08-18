package com.bibash.canteenproject.api.itemvote;

import com.bibash.canteenproject.api.Item.Item;
import com.bibash.canteenproject.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item_vote")
public class ItemVote extends BaseEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Item item;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dis_likes")
    private Integer disLikes;
}
