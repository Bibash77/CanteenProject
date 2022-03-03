package com.bibash.matchella.api.User.repository;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.matchella.api.User.User;
import com.bibash.matchella.core.BaseSpecBuilder;

public class UserSpecBuilder extends BaseSpecBuilder<User> {

    public UserSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<User> getSpecification(String property, String filterValue) {
        return new UserSpec(property , filterValue);
    }
}
