package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Category> getAllCategories() throws DAOException {
        List<Category> categories = entityManager
                .createQuery("select u from Category u").getResultList();

        return categories;
    }

    @Override
    @Transactional
    public Category getCategoryByTag(String tag) throws DAOException {
        Category category;
        try {
            category = (Category) entityManager
                    .createQuery("select u from Category u where u.tag =: tag")
                    .setParameter("tag", tag).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("Category not found by tag: " + tag, e);
        }

        return category;
    }
}
