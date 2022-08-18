package com.bibash.canteenproject.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T> {

    /**
     *
     */
    List<T> findAll();

    /**
     *
     */
    T findOne(Long id);

    /**
     *
     */
    T save(T t);

    /**
     *
     */
    Page<T> findAllPageable(Object t, Pageable pageable);

    /**
     *
     */
    List<T> saveAll(List<T> list);


}
