package com.bibash.canteenproject.api.User.repository;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.canteenproject.api.User.User;
import com.bibash.canteenproject.core.BaseSpecBuilder;

public class UserSpecBuilder extends BaseSpecBuilder<User> {

    public UserSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<User> getSpecification(String property, String filterValue) {
        return new UserSpec(property , filterValue);
    }
}
