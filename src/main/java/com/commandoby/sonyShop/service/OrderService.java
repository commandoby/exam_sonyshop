package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface OrderService {

    Order read(int id) throws ServiceException;

    void delete(int id) throws ServiceException;

    int createOrderByUser(Order order, int userId) throws ServiceException;

    void addProductByOrder(int orderId, int productId) throws ServiceException;

    List<Order> readAllOrdersByUser(int userId) throws ServiceException;

    List<Integer> readAllProductsIdByOrder(int orderId) throws ServiceException;
}
