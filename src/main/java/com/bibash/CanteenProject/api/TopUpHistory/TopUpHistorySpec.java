package com.bibash.CanteenProject.api.TopUpHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.domain.Specification;


public class TopUpHistorySpec implements Specification<TopUpHistory> {
    private static final String FILTER_BY_DATE="date";
    private static final String FILTER_BY_USER_ID= "userId";
    private final String property;
    private final String value;

    @Override
    public Predicate toPredicate(Root<TopUpHistory> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_DATE:
                try {
                    Map dates = new ObjectMapper().readValue(value, Map.class);
                    return criteriaBuilder.or(criteriaBuilder.between(root.get("createdAt"),
                        new SimpleDateFormat("MM/dd/yyyy")
                            .parse(String.valueOf(dates.get("startDate"))),
                        new SimpleDateFormat("MM/dd/yyyy")
                            .parse(String.valueOf(dates.get("endDate")))));
                } catch (JsonProcessingException | ParseException e) {
                    e.printStackTrace();
                }

             case FILTER_BY_USER_ID:
                return criteriaBuilder
                    .or(criteriaBuilder
                        .equal(root.get("userId"), Long.valueOf(value)));

            default:
                return null;
        }
    }

    public  TopUpHistorySpec(String property, String value) {
        this.property = property;
        this.value = value;
    }
}
