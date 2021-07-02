package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public int create(Category category) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        return category.getId();
    }

    @Override
    public Category read(int id) throws ServiceException {
        return entityManager.find(Category.class, id);
    }

    @Override
    public void update(Category category) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Category category) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.remove(category);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        return categoryDao.getAllCategories();
    }

    @Override
    public Category getCategoryByTag(String tag) throws ServiceException {
        return categoryDao.getCategoryByTag(tag);
    }
}
