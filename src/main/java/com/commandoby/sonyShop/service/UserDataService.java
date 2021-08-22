package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface UserDataService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;

    List<User> findUsersByEmailLike(String email) throws ServiceException;
}
