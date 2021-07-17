package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.repository.domain.Product;

import java.util.List;

public interface SearchProductsRepository {
    List<Product> searchProductsByParams(String search_value, String category_tag, String search_comparing,
                                         Integer min_price, Integer max_price) throws RepositoryException;
}
