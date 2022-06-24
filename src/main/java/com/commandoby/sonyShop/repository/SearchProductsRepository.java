package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.Product;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface SearchProductsRepository {
	Page<Product> searchProductsByParams(Map<String, String> psm, Map<String, Integer> pim) throws RepositoryException;
}
