package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.UserDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int create(User user) throws ServiceException {
        return userDao.create(user);
    }

    @Override
    public User read(int id) throws ServiceException {
        return userDao.read(id);
    }

    @Override
    public void update(User user) throws ServiceException {
        userDao.update(user);
    }

    @Override
    public void delete(int id) throws ServiceException {
        userDao.delete(id);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userDao.getUserByEmail(email);
    }

}
