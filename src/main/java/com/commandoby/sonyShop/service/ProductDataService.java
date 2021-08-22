package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;
import java.util.Map;

public interface ProductDataService extends BaseService<Product> {

    List<Product> getAllProducts() throws ServiceException;

    List<Product> getAllProductsByCategory(Category category) throws ServiceException;

    Product getProductByName(String name) throws ServiceException;

    List<Product> getProductsByCategoryAndQuantityNotNull(Category category) throws ServiceException;

    List<Product> getSearchProductsByParams(Map<String, String> paramsStringMap,
                                            Map<String, Integer> paramsIntegerMap) throws ServiceException;
}
