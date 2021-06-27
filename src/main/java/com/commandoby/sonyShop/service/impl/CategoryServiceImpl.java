package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int create(Category category) throws ServiceException {
        category.setId(categoryDao.create(category));
        return category.getId();
    }

    @Override
    public Category read(int id) throws ServiceException {
        return categoryDao.read(id);
    }

    @Override
    public void update(Category category) throws ServiceException {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id) throws ServiceException {
        categoryDao.delete(id);
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        return categoryDao.getAllCategories();
    }
}
