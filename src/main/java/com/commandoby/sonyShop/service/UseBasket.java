package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;

public interface UseBasket {

    Product addProductToBasket(Order order, int product_id) throws ServiceException;

    void removeProductWithOfBasket(Order order, int id) throws NoFoundException, ServiceException;
}
