package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> getAllProducts() throws DAOException;

    List<Product> getAllProductsByCategory(Category category) throws DAOException;

    Product getProductByName(String name) throws DAOException;

    int getCategoryId(int productId) throws DAOException;
}
