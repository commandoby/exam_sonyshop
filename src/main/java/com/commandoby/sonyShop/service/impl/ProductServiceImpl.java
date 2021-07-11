package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.impl.ProductDaoImpl;
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
/*public ProductServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        productDao = new ProductDaoImpl();
    }*/

    @Override
    @Transactional
    public int create(Product product) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.persist(product);
//        entityManager.getTransaction().commit();
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
//        entityManager.getTransaction().begin();
        entityManager.persist(product);
//        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Product product) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.remove(product);
//        entityManager.getTransaction().commit();
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
