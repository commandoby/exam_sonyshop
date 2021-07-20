package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface ProductService extends BaseService<Product> {

    List<Product> getAllProducts() throws ServiceException;

    List<Product> getAllProductsByCategory(Category category) throws ServiceException;

    Product getProductByName(String name) throws ServiceException;

    List<Product> getProductsByNameLike(String text) throws ServiceException;

    List<Product> getProductsByDescriptionLike(String text) throws ServiceException;

    List<Product> getProductsByNotNullQuantity() throws ServiceException;
}
