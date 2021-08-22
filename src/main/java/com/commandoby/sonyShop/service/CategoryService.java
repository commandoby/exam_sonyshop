package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.exceptions.ServiceException;
import org.springframework.ui.ModelMap;

public interface CategoryService extends CategoryDataService {

    ModelMap getHomePageModelMap(ModelMap modelMap) throws ServiceException;
}
