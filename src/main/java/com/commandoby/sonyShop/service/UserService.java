package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;

public interface UserService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;
}
