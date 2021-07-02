package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.DAOException;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public List<Product> getAllProducts() throws DAOException {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager.createQuery("select u from Product u").getResultList();
        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) throws DAOException {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.category =: category")
                .setParameter("category", category).getResultList();
        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Product getProductByName(String name) throws DAOException {
        entityManager.getTransaction().begin();
        Product product = (Product) entityManager
                .createQuery("select u from Product u where u.name =: name")
                .setParameter("name", name).getSingleResult();
        entityManager.getTransaction().commit();

        return product;
    }

    @Override
    public List<Product> getProductsByNameLike(String text) throws DAOException {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.name like :text")
                .setParameter("text", "%" + text + "%").getResultList();
        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public List<Product> getProductsByDescriptionLike(String text) throws DAOException {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.description like :text")
                .setParameter("text", "%" + text + "%").getResultList();
        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public List<Product> getProductsByNotNullQuantity() throws DAOException {
        entityManager.getTransaction().begin();
        List<Product> products = entityManager
                .createQuery("select u from Product u where u.quantity > 0").getResultList();
        entityManager.getTransaction().commit();

        return products;
    }
}
