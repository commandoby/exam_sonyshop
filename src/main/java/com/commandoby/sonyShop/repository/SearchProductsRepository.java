package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.domain.Product;

import java.util.List;

public interface SearchProductsRepository {
    List<Product> searchProductsByParams(String searchValue, String categoryTag, String searchComparing,
                                         String isQuantity, Integer minPrice, Integer maxPrice) throws RepositoryException;
}
