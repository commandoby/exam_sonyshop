package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<User> getAllUsers() throws ServiceException;

    User getUserByEmail(String email) throws ServiceException;

    List<String> getAllUsersEmails() throws ServiceException;

    int getUserBalanceByEmail(String email) throws ServiceException;

    List<User> findUsersByEmailLike(String email) throws ServiceException;
}
