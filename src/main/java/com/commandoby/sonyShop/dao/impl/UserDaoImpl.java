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

//    public UserDaoImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    @Transactional
    public List<User> getAllUsers() throws DAOException {
//        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User u").getResultList();
//        entityManager.getTransaction().commit();

        return users;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) throws DAOException {
        User user;
        try {
//            entityManager.getTransaction().begin();
            user = (User) entityManager
                    .createQuery("select u from User u where u.email =: email")
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("User not found by email: " + email, e);
        } /*finally {
            entityManager.getTransaction().commit();
        }*/

        return user;
    }

    @Override
    @Transactional
    public List<String> getAllUsersEmails() throws DAOException {
//        entityManager.getTransaction().begin();
        List<String> emailList = entityManager
                .createQuery("select u.email from User u").getResultList();
//        entityManager.getTransaction().commit();

        return emailList;
    }

    @Override
    @Transactional
    public List<User> findUsersByEmailLike(String email) throws DAOException {
//        entityManager.getTransaction().begin();
        List<User> users = entityManager
                .createQuery("select u from User u where u.email like :email")
                .setParameter("email", "%" + email + "%").getResultList();
//        entityManager.getTransaction().commit();

        return users;
    }

}
