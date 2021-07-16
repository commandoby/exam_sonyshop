package com.commandoby.sonyShop.dao.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getAllUsers() throws DAOException {
        List<User> users = entityManager.createQuery("select u from User u").getResultList();

        return users;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) throws DAOException {
        User user;
        try {
            user = (User) entityManager
                    .createQuery("select u from User u where u.email =: email")
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("User not found by email: " + email, e);
        }

        return user;
    }

    @Override
    @Transactional
    public List<String> getAllUsersEmails() throws DAOException {
        List<String> emailList = entityManager
                .createQuery("select u.email from User u").getResultList();

        return emailList;
    }

    @Override
    @Transactional
    public List<User> findUsersByEmailLike(String email) throws DAOException {
        List<User> users = entityManager
                .createQuery("select u from User u where u.email like :email")
                .setParameter("email", "%" + email + "%").getResultList();

        return users;
    }

}
