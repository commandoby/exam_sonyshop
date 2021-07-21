package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.CategoryRepository;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public int create(Category category) throws ServiceException {
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public Category read(int id) throws ServiceException {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ServiceException("Error retrieving a category from the database by ID: " + id + ".", new Exception())
        );
    }

    @Override
    public void update(Category category) throws ServiceException {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) throws ServiceException {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> gelAllCategorySortByRating() throws ServiceException {
        return categoryRepository.gelAllCategorySortByRating();
    }

    @Override
    public Category getCategoryByTag(String tag) throws ServiceException {
        return categoryRepository.findCategoryByTag(tag);
    }
}
