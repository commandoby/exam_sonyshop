package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> getAllProducts() throws ServiceException;

    List<Product> getAllProductsByCategory(Category category) throws ServiceException;

    Product getProductByName(String name) throws ServiceException;

    List<Product> getProductsByCategoryAndQuantityNotNull(Category category) throws ServiceException;

    List<Product> getSearchProductsByParams(String searchValue, String categoryTag, String searchComparing,
                                            String isQuantity, Integer minPrice, Integer maxPrice) throws ServiceException;
}
