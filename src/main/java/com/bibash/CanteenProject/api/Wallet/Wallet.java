package com.bibash.CanteenProject.api.Wallet;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.BaseEntity;

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
