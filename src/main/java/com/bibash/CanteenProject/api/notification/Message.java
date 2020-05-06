package com.bibash.CanteenProject.api.notification;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.core.enums.Status;
import com.bibash.CanteenProject.core.enums.TransactionType;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private Long fromRole;
    private Long toId;
    private Long fromId;
    private String message;
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private TransactionType transactionType;
}