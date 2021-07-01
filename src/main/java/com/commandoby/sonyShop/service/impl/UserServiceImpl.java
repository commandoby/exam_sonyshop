package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.UserDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    @Override
    public int create(User user) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user.getId();
    }

    @Override
    public User read(int id) throws ServiceException {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void update(User user) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(User user) throws ServiceException {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<String> getAllUsersEmails() throws ServiceException {
        return userDao.getAllUsersEmails();
    }

    @Override
    public List<User> findUsersByEmailLike(String email) throws ServiceException {
        return userDao.findUsersByEmailLike(email);
    }
}
