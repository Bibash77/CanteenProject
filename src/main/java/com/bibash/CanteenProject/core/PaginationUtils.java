package com.bibash.CanteenProject.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author Bibash Bogati on 2/18/2019
 */

public class PaginationUtils {

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String SORT_BY = "sortBy";
    public static final String SORT_ORDER = "sortOrder";

    public static Pageable pageable(int page, int size) {
        return PageRequest.of(page - 1, size);
    }

    public static Map<String, String> excludePageableProperties(Map<String, String> requestParam) {
        final List<String> pageableProperties = Arrays.asList(PAGE, SIZE, SORT_BY, SORT_ORDER);

        return requestParam.keySet().stream()
            .filter(k -> !pageableProperties.contains(k))
            .collect(Collectors.toMap(k -> k, requestParam::get));
    }
}
