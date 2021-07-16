package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public int create(Product product) throws ServiceException {
        entityManager.persist(product);
        return product.getId();
    }

    @Override
    @Transactional
    public Product read(int id) throws ServiceException {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public void update(Product product) throws ServiceException {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void delete(Product product) throws ServiceException {
        entityManager.remove(product);
    }

    @Override
    public List<Product> getAllProducts() throws ServiceException {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws ServiceException {
        return productDao.getAllProductsByCategory(category);
    }

    @Override
    public Product getProductByName(String name) throws ServiceException {
        return productDao.getProductByName(name);
    }

    @Override
    public List<Product> getProductsByNameLike(String text) throws ServiceException {
        return productDao.getProductsByNameLike(text);
    }

    @Override
    public List<Product> getProductsByDescriptionLike(String text) throws ServiceException {
        return productDao.getProductsByDescriptionLike(text);
    }

    @Override
    public List<Product> getProductsByNotNullQuantity() throws ServiceException {
        return productDao.getProductsByNotNullQuantity();
    }
}
