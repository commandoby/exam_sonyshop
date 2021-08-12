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


    ModelAndView register(String name, String surname, String date_of_birth,
                          String email, String password, String second_password) throws ServiceException;

    void userPayMethod(User user, Order order) throws ServiceException;

    boolean validateUser(ModelAndView modelAndView, BindingResult bindingResult, User user) throws ServiceException;

    boolean duplicateCheck(String email) throws ServiceException;

    boolean validateLocalData(String date) throws ServiceException;

    ModelAndView editUserData(User user, String new_name, String new_surname,
                              String new_date_of_birth, String old_password) throws ServiceException;

    ModelAndView editUserPassword(User user, String new_password, String old_password,
                                  String second_password) throws ServiceException;
}
