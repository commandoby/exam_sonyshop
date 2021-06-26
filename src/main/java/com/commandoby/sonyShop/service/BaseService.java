package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.BaseEntity;
import com.commandoby.sonyShop.exceptions.ServiceException;

public interface BaseService<T extends BaseEntity> {

    int create(T entity) throws ServiceException;

    T read(int id) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(int id) throws ServiceException;

}