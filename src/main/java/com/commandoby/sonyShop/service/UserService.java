package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

import java.util.List;

public interface UserService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;

    List<User> findUsersByEmailLike(String email) throws ServiceException;
}
