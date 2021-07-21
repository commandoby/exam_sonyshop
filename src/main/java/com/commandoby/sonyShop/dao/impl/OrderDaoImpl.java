package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Order> readAllOrdersByUser(User user) throws DAOException {
        List<Order> orders = entityManager
                .createQuery("select u from Order u where u.user =: user")
                .setParameter("user", user).getResultList();

        return orders;
    }
}
