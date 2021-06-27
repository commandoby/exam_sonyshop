package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface CategoryDao extends BaseDao<Category>{

    List<Category> getAllCategories() throws DAOException;
}
