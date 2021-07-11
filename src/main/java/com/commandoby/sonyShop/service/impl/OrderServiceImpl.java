package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.OrderDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
/*public OrderServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        orderDao = new OrderDaoImpl();
    }*/

    @Override
    @Transactional
    public int create(Order order) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.persist(order);
//        entityManager.getTransaction().commit();
        return order.getId();
    }

    @Override
    @Transactional
    public Order read(int id) throws ServiceException {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Transactional
    public void update(Order order) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.persist(order);
//        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(Order order) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.remove(order);
//        entityManager.getTransaction().commit();
    }

    @Override
    public List<Order> readAllOrdersByUser(User user) throws ServiceException {
        return orderDao.readAllOrdersByUser(user);
    }
}
