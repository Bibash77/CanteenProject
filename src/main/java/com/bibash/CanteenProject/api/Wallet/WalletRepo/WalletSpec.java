package com.bibash.CanteenProject.api.Wallet.WalletRepo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.Wallet.Wallet;
import com.bibash.CanteenProject.core.enums.Status;

public class WalletSpec implements Specification<Wallet> {

    private static final String FILTER_BY_USERNAME = "userName";
    private static final String FILTER_BY_USER_STATUS = "userStatus";
    private static final String FILTER_BY_USERCODE = "userCode";
    private final String property;
    private final String value;

    public WalletSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }


    @Override
    public Predicate toPredicate(Root<Wallet> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {

            case FILTER_BY_USERCODE:
                return criteriaBuilder.and(criteriaBuilder.like(root.join("user").get("userCode"),
                    value+"%"));

            case FILTER_BY_USERNAME:
                return criteriaBuilder.and(criteriaBuilder.like(root.join("user").get("userName"),
                    value+"%"));
            case FILTER_BY_USER_STATUS:
                return criteriaBuilder.and(criteriaBuilder
                    .equal(root.join("user").get("status"),
                        Status.valueOf(value)));
            default:
                return null;
        }
    }
}
