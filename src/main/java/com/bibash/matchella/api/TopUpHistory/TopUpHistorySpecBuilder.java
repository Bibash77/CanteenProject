package com.bibash.matchella.api.TopUpHistory;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.bibash.matchella.core.BaseSpecBuilder;

public class TopUpHistorySpecBuilder  extends BaseSpecBuilder<TopUpHistory> {

    public TopUpHistorySpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<TopUpHistory> getSpecification(String property, String filterValue) {
        return new TopUpHistorySpec(property, filterValue);
    }


}
