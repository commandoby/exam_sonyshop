package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.UserDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
/*public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        userDao = new UserDaoImpl();
    }*/

    @Override
    @Transactional
    public int create(User user) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();
        return user.getId();
    }

    @Override
    @Transactional
    public User read(int id) throws ServiceException {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void update(User user) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void delete(User user) throws ServiceException {
//        entityManager.getTransaction().begin();
        entityManager.remove(user);
//        entityManager.getTransaction().commit();
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
