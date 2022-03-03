package com.bibash.matchella.api.TopUpHistory;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.matchella.core.enums.TransactionType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TopUpHistory {
    @Id
    @GeneratedValue
    private Long id;

    private double topUpAmount;

    private TransactionType transactionType;

    private double remainingAmount;

    private Long userId;

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt = new Date();
}
