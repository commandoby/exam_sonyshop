package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.dao.impl.ProductDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int create(Product product) throws ServiceException {
        return productDao.create(product);
    }

    @Override
    public Product read(int id) throws ServiceException {
        Product product = productDao.read(id);
        product.setCategory(getCategory(product));
        return product;
    }

    @Override
    public void update(Product product) throws ServiceException {
        productDao.update(product);
    }

    @Override
    public void delete(int id) throws ServiceException {
        productDao.delete(id);
    }

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        List<Product> productList = productDao.getAllProducts();
        for (Product product : productList) product.setCategory(getCategory(product));
        return productList;
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws ServiceException {
        List<Product> productList = productDao.getAllProductsByCategory(category);
        for (Product product : productList) product.setCategory(getCategory(product));
        return productList;
    }

    @Override
    public Product getProductByName(String name) throws ServiceException {
        Product product = productDao.getProductByName(name);
        product.setCategory(getCategory(product));
        return product;
    }

    @Override
    public int getCategoryId(int productId) throws ServiceException {
        return productDao.getCategoryId(productId);
    }

    private Category getCategory(Product product) throws ServiceException {
        int categoryId = productDao.getCategoryId(product.getId());
        return categoryDao.read(categoryId);
    }
}
