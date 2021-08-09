package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    List<Order> readOrdersByUser(User user) throws ServiceException;
}
