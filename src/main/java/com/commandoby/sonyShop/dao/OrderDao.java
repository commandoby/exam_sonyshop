package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    int createOrderByUser(Order order, int userId) throws DAOException;

    void addProductByOrder(int orderId, int productId) throws DAOException;

    List<Order> readAllOrdersByUser(int userId) throws DAOException;

    List<Integer> readAllProductsIdByOrder(int orderId) throws DAOException;
}
