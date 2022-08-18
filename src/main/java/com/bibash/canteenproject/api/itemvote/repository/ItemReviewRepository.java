package com.bibash.canteenproject.api.itemvote.repository;

import com.bibash.canteenproject.api.itemvote.ItemReview;
import com.bibash.canteenproject.api.notification.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemReviewRepository extends JpaRepository<ItemReview, Long>,
    JpaSpecificationExecutor<ItemReview> {

}
