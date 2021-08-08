package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.CategoryRepository;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = Logger.getLogger(getClass());
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
    public List<Category> getCategories() throws ServiceException {
        Optional<List<Category>> categories = Optional.of(categoryRepository.findAll());
        return categories.orElseThrow(() ->
                new ServiceException("Error getting a list of all categories.", new Exception()));
    }

    @Override
    public List<Category> getCategoriesSortByRating() throws ServiceException {
        Optional<List<Category>> categories = Optional.of(categoryRepository.gelAllCategorySortByRating());
        return categories.orElseThrow(() ->
                new ServiceException("Error getting categories sort by rating.", new Exception()));
    }

    @Override
    public Category getCategoryByTag(String tag) throws ServiceException {
        Optional<Category> categories = Optional.of(categoryRepository.findCategoryByTag(tag));
        return categories.orElseThrow(() ->
                new ServiceException("Error retrieving category by tag: " + tag + ".", new Exception()));
    }

    @Override
    public Category getCategoryForProducts(String categoryTag) throws ServiceException {
        Category category = getCategoryByTag(categoryTag);
        category.setRating(category.getRating() + 1);
        try {
            update(category);
        } catch (ServiceException e) {
            log.error("Category rating update failed.", e);
        }
        return category;
    }
}
