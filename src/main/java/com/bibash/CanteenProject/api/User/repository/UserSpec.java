package com.bibash.CanteenProject.api.User.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.User.User;

public class UserSpec implements Specification<User> {

    private static final String FILTER_BY_USER_NAME= "userName";
    private static final String FILTER_BY_USER_CODE= "userCode";

    private final String property;
    private final String value;

    public UserSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
       switch (property){
           case FILTER_BY_USER_NAME:
               return criteriaBuilder.like(root.get(property), "%" + value + "%");
           case FILTER_BY_USER_CODE:
               return criteriaBuilder.like(root.get(property), value);
           default:
               return null;
       }
    }
}
