package com.bibash.canteenproject.api.Wallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.bibash.canteenproject.api.User.User;
import com.bibash.canteenproject.core.BaseEntity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
@EqualsAndHashCode(callSuper = true)
public class Wallet extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    private User user;

    private Double walletExpenditure;

    private Double walletAmount;

    @Transient
    private Double depositAmount;
}
