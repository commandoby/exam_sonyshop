package com.commandoby.sonyShop.service;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface UserValidate {

    boolean validateUser(ModelAndView modelAndView, BindingResult bindingResult, User user) throws ServiceException;

    boolean checkReceivedUser(ModelAndView modelAndView, User user) throws ServiceException;
}
