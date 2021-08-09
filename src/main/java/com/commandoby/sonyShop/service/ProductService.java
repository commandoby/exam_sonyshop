package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService extends BaseService<Product> {

    List<Product> getAllProducts() throws ServiceException;

    List<Product> getAllProductsByCategory(Category category) throws ServiceException;

    Product getProductByName(String name) throws ServiceException;

    List<Product> getProductsByCategoryAndQuantityNotNull(Category category) throws ServiceException;

    List<Product> getSearchProductsByParams(Map<String, Optional<String>> paramsStringMap,
                                            Map<String, Optional<Integer>> paramsIntegerMap) throws ServiceException;


    void prePagination(ModelMap modelMap, List<Product> products,
                              Integer pageItems, Integer pageNumber) throws ServiceException;

    Map<String, Optional<String>> defaultParamsStringMap(
            Map<String, Optional<String>> paramsStringMap) throws ServiceException;

    Map<String, Optional<Integer>> defaultParamsIntegerMap(
            Map<String, Optional<Integer>> paramsIntegerMap) throws ServiceException;
}
