package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> getAllProducts() throws ServiceException;

    List<Product> getAllProductsByCategory(Category category) throws ServiceException;

    Product getProductByName(String name) throws ServiceException;

    List<Product> getProductsByNameLike(String text) throws ServiceException;

    List<Product> getProductsByDescriptionLike(String text) throws ServiceException;

    List<Product> getProductsByNotEmptyQuantity() throws ServiceException;

    List<Product> getSearchProductsByParams(String search_value, String category_tag, String search_comparing,
                                            Integer min_price, Integer max_price) throws ServiceException;
}
