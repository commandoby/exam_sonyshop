package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public List<User> getAllUsers() throws DAOException {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User u").getResultList();
        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        User user = null;
        try {
            entityManager.getTransaction().begin();
            user = (User) entityManager
                    .createQuery("select u from User u where u.email =: email")
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("User not found by email.", e);
        } finally {
            entityManager.getTransaction().commit();
        }

        return user;
    }

    @Override
    public List<String> getAllUsersEmails() throws DAOException {
        entityManager.getTransaction().begin();
        List<String> emailList = entityManager
                .createQuery("select u.email from User u").getResultList();
        entityManager.getTransaction().commit();

        return emailList;
    }

    @Override
    public int getUserBalanceByEmail(String email) throws DAOException {
        entityManager.getTransaction().begin();
        int userBalance = entityManager
                .createQuery("select u.balance from User u where u.email =: email")
                .setParameter("email", email).getFirstResult();
        entityManager.getTransaction().commit();
        System.out.println(userBalance);
        return userBalance;
    }

    @Override
    public List<User> findUsersByEmailLike(String email) throws DAOException {
        entityManager.getTransaction().begin();
        List<User> users = entityManager
                .createQuery("select u from User u where u.email like :email")
                .setParameter("email", "%" + email + "%").getResultList();
        entityManager.getTransaction().commit();

        return users;
    }

}
