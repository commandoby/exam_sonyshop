package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public List<Order> readAllOrdersByUser(User user) throws DAOException {
        entityManager.getTransaction().begin();
        List<Order> orders = entityManager
                .createQuery("select u from Order u where u.user =: user")
                .setParameter("user", user).getResultList();
        entityManager.getTransaction().commit();

        return orders;
    }
}
