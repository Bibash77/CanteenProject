package com.bibash.CanteenProject.api.notification.service;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.CanteenProject.api.notification.Message;
import com.bibash.CanteenProject.core.BaseSpecBuilder;

public class NotificationSpecBuilder extends BaseSpecBuilder<Message> {

    public NotificationSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Message> getSpecification(String property, String filterValue) {
        return new NotificationSpec(property , filterValue);
    }
}
