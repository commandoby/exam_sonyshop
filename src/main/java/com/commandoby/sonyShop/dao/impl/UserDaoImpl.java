package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
        entityManager.getTransaction().begin();
        User user = (User) entityManager
                .createQuery("select u from User u where u.email =: email")
                .setLockMode(LockModeType.OPTIMISTIC)
                .setParameter("email", email).getSingleResult();
        entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public List<String> getAllUsersEmails() throws DAOException {
        entityManager.getTransaction().begin();
        List<String> emailList = entityManager.createQuery("select u.email from User u").getResultList();
        entityManager.getTransaction().commit();

        return emailList;
    }

    @Override
    public List<User> findUsersByEmailLike(String email) throws DAOException {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User u where u.email like :email")
                .setParameter("email", "%" + email + "%").getResultList();
        entityManager.getTransaction().commit();

        return users;
    }

}
