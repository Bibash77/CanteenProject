package com.bibash.CanteenProject.api.User;

import java.math.BigInteger;
import java.util.List;

import com.bibash.CanteenProject.api.OrderItem.ItemOrder;
import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.core.BaseEntity;
import com.bibash.CanteenProject.core.enums.RoleType;
import com.bibash.CanteenProject.core.enums.Status;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "Full_Name")
    private String fullName;

    @Column(nullable = false)
    private RoleType roleType;

    @Column(nullable = false)
    private String userCode;

    @Column(nullable = false)
    private String password;

    private String Batch;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany
    private List<ItemOrder> itemOrder;

    @Column
    private Double walletAmount;

}
