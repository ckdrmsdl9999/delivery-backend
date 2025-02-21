package com.sparta.delivery.domain.store.repository;

import com.sparta.delivery.domain.store.entity.Stores;
import com.sparta.delivery.domain.store.enums.Category;

import java.util.List;

public interface StoreRepositoryCustom {

    List<Stores> findByNameContainingAndCategoryAndDeletedAtIsNull(String name, Category category);

    List<Stores> findByCategory(Category category);
}
