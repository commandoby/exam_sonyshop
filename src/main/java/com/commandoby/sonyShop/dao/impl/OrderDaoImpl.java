package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private EntityManager entityManager;

    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Order> readAllOrdersByUser(User user) throws DAOException {
        entityManager.getTransaction().begin();
        List<Order> orders = entityManager
                .createQuery("select u from Order u where u.user =: user")
                .setParameter("user", user).getResultList();
        entityManager.getTransaction().commit();

        return orders;
    }
}
