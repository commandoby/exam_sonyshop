package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;

public interface OrderService extends OrderDataService {

    void addProductToBasket(Order order, Product product) throws ServiceException;

    void removeProductWithOfBasketByNumber(Order order, int number) throws NotFoundException, ServiceException;

    void removeProductWithOfBasket(Order order, Product product) throws NotFoundException, ServiceException;

    void orderPayMethod(User user, Order order) throws ServiceException;
}
