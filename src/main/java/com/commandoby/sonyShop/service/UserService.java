package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService extends BaseService<User> {

    User getUserByEmail(String email) throws ServiceException;

    List<User> findUsersByEmailLike(String email) throws ServiceException;


    void userPayMethod(User user, Order order) throws ServiceException;

    boolean validateUser(ModelAndView modelAndView, BindingResult bindingResult, User user) throws ServiceException;

    boolean duplicateCheck(String email);

    boolean validateLocalData(String date);
}
