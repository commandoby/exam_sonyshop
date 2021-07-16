package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> getAllProducts() throws DAOException {
        List<Product> products = entityManager.createQuery("select u from Product u").getResultList();

        return products;
    }

    @Override
    @Transactional
    public List<Product> getAllProductsByCategory(Category category) throws DAOException {
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.category =: category")
                .setParameter("category", category).getResultList();

        return products;
    }

    @Override
    @Transactional
    public Product getProductByName(String name) throws DAOException {
        Product product;
        try {
            product = (Product) entityManager
                    .createQuery("select u from Product u where u.name =: name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("Product not found by name: " + name, e);
        }

        return product;
    }

    @Override
    @Transactional
    public List<Product> getProductsByNameLike(String text) throws DAOException {
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.name like :text")
                .setParameter("text", "%" + text + "%").getResultList();

        return products;
    }

    @Override
    @Transactional
    public List<Product> getProductsByDescriptionLike(String text) throws DAOException {
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.description like :text")
                .setParameter("text", "%" + text + "%").getResultList();

        return products;
    }

    @Override
    @Transactional
    public List<Product> getProductsByNotNullQuantity() throws DAOException {
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.quantity > 0").getResultList();

        return products;
    }
}
