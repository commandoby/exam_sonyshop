package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface ProductDao {

    List<Product> getAllProducts() throws DAOException;

    List<Product> getAllProductsByCategory(Category category) throws DAOException;

    Product getProductByName(String name) throws DAOException;

    List<Product> getProductsByNameLike(String text) throws DAOException;

    List<Product> getProductsByDescriptionLike(String text) throws DAOException;

    List<Product> getProductsByNotNullQuantity() throws DAOException;
}
