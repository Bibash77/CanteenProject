package com.bibash.CanteenProject.api.Wallet;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.api.User.User;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private Double walletExpenditure;

    private Double walletAmount;

    @Transient
    private Double depositAmount;
}
