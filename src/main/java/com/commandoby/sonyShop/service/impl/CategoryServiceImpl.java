package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @PersistenceContext
    private EntityManager entityManager;
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public int create(Category category) throws ServiceException {
        entityManager.persist(category);
        return category.getId();
    }

    @Override
    @Transactional
    public Category read(int id) throws ServiceException {
        return entityManager.find(Category.class, id);
    }

    @Override
    @Transactional
    public void update(Category category) throws ServiceException {
        entityManager.merge(category);
    }

    @Override
    @Transactional
    public void delete(Category category) throws ServiceException {
        entityManager.remove(category);
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
