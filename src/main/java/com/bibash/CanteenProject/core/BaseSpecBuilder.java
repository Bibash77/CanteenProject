package com.bibash.CanteenProject.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecBuilder<T> {
    protected final Map<String, String> params;

    public BaseSpecBuilder(Map<String, String> params) {
        this.params = params;
    }
    public Specification<T> build() {
        if ( params == null || params.isEmpty()) {
            return null;
        }

        final List<String> properties = new ArrayList<>(params.keySet());

        final String firstProperty = properties.get(0);

        Specification<T> spec = getSpecification(firstProperty, params.get(firstProperty));
        for (int i = 1; i < properties.size(); i++) {
            final String property = properties.get(i);
            spec = Specification.where(spec)
                .and(getSpecification(property, params.get(property)));
        }
        return spec;
    }
    protected abstract Specification<T> getSpecification(String property, String filterValue);
}
