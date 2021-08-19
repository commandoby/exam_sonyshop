package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    List<Order> readOrdersByUser(User user) throws ServiceException;


    Product addProductToBasketById(Order order, int product_id) throws ServiceException;

    void removeProductWithOfBasketByNumber(Order order, int number) throws NotFoundException, ServiceException;

    void removeProductWithOfBasketById(Order order, int id) throws NotFoundException, ServiceException;

    void orderPayMethod(User user, Order order) throws ServiceException;
}
