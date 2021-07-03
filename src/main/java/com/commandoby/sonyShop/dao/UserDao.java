package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.DAOException;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers() throws DAOException;

    User getUserByEmail(String email) throws DAOException;

    List<String> getAllUsersEmails() throws DAOException;

    int getUserBalanceByEmail(String email) throws DAOException;

    List<User> findUsersByEmailLike(String email) throws DAOException;
}
