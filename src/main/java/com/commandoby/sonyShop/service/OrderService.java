package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    List<Order> readAllOrdersByUser(User user) throws ServiceException;
}
