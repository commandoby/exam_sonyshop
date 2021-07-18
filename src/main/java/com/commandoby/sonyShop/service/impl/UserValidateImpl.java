package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.regex.Pattern;

import static com.commandoby.sonyShop.enums.RequestParamEnum.INFO;
import static com.commandoby.sonyShop.enums.RequestParamEnum.USER;

@Service
@Validated
public class UserValidateImpl {

    private final Logger log = Logger.getLogger(getClass());
    private final UserService userService;

    public UserValidateImpl(UserService userService) {
        this.userService = userService;
    }

    public boolean validateUser(ModelAndView modelAndView, BindingResult bindingResult,
                                User user) throws ServiceException {
        if (Optional.ofNullable(user).isPresent()
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

    private boolean checkReceivedUser(ModelAndView modelAndView, User user) throws ServiceException {
        User findUser = null;
        ModelMap modelMap = new ModelMap();
        try {
            findUser = userService.getUserByEmail(user.getEmail());
        } catch (ServiceException e) {
            log.info("Error getting user by email: " + user.getEmail() + ".", e);
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

    private void populateError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + "Error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }

    public boolean duplicateCheck(String email) {
        try {
            User user = userService.getUserByEmail(email);
            if (user != null) return true;
        } catch (ServiceException e) {
            log.warn("Error getting user by email: " + email + ".", e);
        }
        return false;
    }

    public boolean validateLocalData(String date) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        if (date.matches(pattern.pattern())) {
            return true;
        }
        return false;
    }
}
