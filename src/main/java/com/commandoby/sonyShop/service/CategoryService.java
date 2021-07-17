package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface CategoryService extends BaseService<Category>{

    List<Category> getAllCategories() throws ServiceException;

    List<Category> gelAllCategorySortByRating() throws ServiceException;

    Category getCategoryByTag(String tag) throws ServiceException;
}
