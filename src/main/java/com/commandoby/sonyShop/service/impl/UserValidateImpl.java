package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.UserValidate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

import static com.commandoby.sonyShop.enums.RequestParamEnum.INFO;
import static com.commandoby.sonyShop.enums.RequestParamEnum.USER;

@Service
@Validated
public class UserValidateImpl implements UserValidate {

    private final Logger log = Logger.getLogger(getClass());
    private final UserService userService;

    public UserValidateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean validateUser(ModelAndView modelAndView, BindingResult bindingResult,
                                @Valid User user) throws ServiceException {
        if(Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {

            if (bindingResult.hasErrors()) {
                populateError("email", modelAndView, bindingResult);
                populateError("password", modelAndView, bindingResult);

                return false;
            }

            return checkReceivedUser(modelAndView, user);
        }
        return false;
    }

    @Override
    public boolean checkReceivedUser(ModelAndView modelAndView, User user) throws ServiceException {
        User findUser = null;
        ModelMap modelMap = new ModelMap();
        try {
            findUser = userService.getUserByEmail(user.getEmail());
        } catch (ServiceException e) {
            log.info(e);
        }
        if (findUser != null) {
            if (findUser.getPassword().equals(user.getPassword())) {
                modelMap.addAttribute(USER.getValue(), findUser);
                modelAndView.addAllObjects(modelMap);
                return true;
            } else {
                modelMap.addAttribute(INFO.getValue(), "Invalid password.");
            }
        } else {
            modelMap.addAttribute(INFO.getValue(), "User is not found.");
        }
        modelMap.addAttribute(USER.getValue(), null);
        modelAndView.addAllObjects(modelMap);
        return false;
    }

    private void populateError (String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + "Error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }
}
