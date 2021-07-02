package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.OrderDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public int create(Order order) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        return order.getId();
    }

    @Override
    public Order read(int id) throws ServiceException {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void update(Order order) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Order order) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Order> readAllOrdersByUser(User user) throws ServiceException {
        return orderDao.readAllOrdersByUser(user);
    }
}
