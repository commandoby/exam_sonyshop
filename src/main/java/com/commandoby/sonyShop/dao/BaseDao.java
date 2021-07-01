package com.commandoby.sonyShop.dao;

import com.commandoby.sonyShop.dao.domain.BaseEntity;
import com.commandoby.sonyShop.exceptions.DAOException;

@Deprecated
public interface BaseDao<T extends BaseEntity> {

    ConnectionPool databaseConnection = ConnectionPool.getInstance();

    int create(T entity) throws DAOException;

    T read(int id) throws DAOException;

    void update(T entity) throws DAOException;

    void delete(int id) throws DAOException;

}
