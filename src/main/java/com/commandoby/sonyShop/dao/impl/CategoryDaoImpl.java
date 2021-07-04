package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.exceptions.DAOException;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public List<Category> getAllCategories() throws DAOException {
        entityManager.getTransaction().begin();
        List<Category> categories = entityManager
                .createQuery("select u from Category u").getResultList();
        entityManager.getTransaction().commit();

        return categories;
    }

    @Override
    public Category getCategoryByTag(String tag) throws DAOException {
        Category category;
        try {
            entityManager.getTransaction().begin();
            category = (Category) entityManager
                    .createQuery("select u from Category u where u.tag =: tag")
                    .setParameter("tag", tag).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("Category not found by tag: " + tag, e);
        } finally {
            entityManager.getTransaction().commit();
        }

        return category;
    }
}
