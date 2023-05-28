package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.Map;

public interface ProductService extends ProductDataService {

    Map<String, String> defaultParamsStringMap(Map<String, String> paramsStringMap) throws ServiceException;

    Map<String, Integer> defaultParamsIntMap(Map<String, Integer> paramsIntegerMap) throws ServiceException;
}
