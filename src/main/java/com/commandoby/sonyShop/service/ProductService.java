package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface ProductService extends ProductDataService {

    void prePagination(ModelMap modelMap, List<Product> products,
                              Integer pageItems, Integer pageNumber) throws ServiceException;

    Map<String, String> defaultParamsStringMap(Map<String, String> paramsStringMap) throws ServiceException;

    Map<String, Integer> defaultParamsIntegerMap(Map<String, Integer> paramsIntegerMap) throws ServiceException;
}
