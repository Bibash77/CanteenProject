package com.bibash.CanteenProject.api.User.repository;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.User.User;
import com.bibash.CanteenProject.core.BaseSpecBuilder;

public class UserSpecBuilder extends BaseSpecBuilder<User> {

    public UserSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<User> getSpecification(String property, String filterValue) {
        return new UserSpec(property , filterValue);
    }
}
