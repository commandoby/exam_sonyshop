package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SearchProductsRepository {
    List<Product> searchProductsByParams(Map<String, String> paramsStringMap,
                                         Map<String, Integer> paramsIntegerMap) throws RepositoryException;
}
