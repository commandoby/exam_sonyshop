package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface OrderService extends BaseService<Order> {

    List<Order> readOrdersByUser(User user) throws ServiceException;
}
