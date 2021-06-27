package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;

public interface UserDao extends BaseDao<User> {

    User getUserByEmail(String email) throws DAOException;
}
