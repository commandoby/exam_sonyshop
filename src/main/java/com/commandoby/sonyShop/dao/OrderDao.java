package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface OrderDao {

    List<Order> readAllOrdersByUser(User user) throws DAOException;
}
