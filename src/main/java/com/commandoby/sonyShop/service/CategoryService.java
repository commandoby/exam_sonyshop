package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface CategoryService extends BaseService<Category>{

    List<Category> getCategories() throws ServiceException;

    List<Category> getCategoriesSortByRating() throws ServiceException;

    Category getCategoryByTag(String tag) throws ServiceException;

    Category getCategoryForProducts(String categoryTag) throws ServiceException;
}
