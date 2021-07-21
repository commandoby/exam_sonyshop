package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMethodsImpl {

    private final Logger log = Logger.getLogger(getClass());
    private final CategoryService categoryService;

    public CategoryMethodsImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getCategories() {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException e) {
            log.warn("Error getting categories.", e);
        }
        return categories;
    }

    public List<Category> getCategoriesSortByRating() {
        List<Category> categories = null;
        try {
            categories = categoryService.gelAllCategorySortByRating();
        } catch (ServiceException e) {
            log.warn("Error getting categories sort by rating.", e);
        }
        return categories;
    }

    public Category getCategory(String categoryTag) {
        Category category = null;
        try {
            category = categoryService.getCategoryByTag(categoryTag);
        } catch (ServiceException e) {
            log.warn("Error retrieving category by tag: " + categoryTag + ".", e);
        }
        return category;
    }

    public Category getCategoryForProducts(String categoryTag) {
        Category category = getCategory(categoryTag);
        category.setRating(category.getRating() + 1);
        try {
            categoryService.update(category);
        } catch (ServiceException e) {
            log.error("Category rating update failed.", e);
        }
        return category;
    }
}
