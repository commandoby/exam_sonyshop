package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.repository.UserRepository;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.commandoby.sonyShop.enums.RequestParamEnum.INFO;
import static com.commandoby.sonyShop.enums.RequestParamEnum.USER;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int create(User user) throws ServiceException {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User read(int id) throws ServiceException {
        return userRepository.findById(id).orElseThrow(() ->
                new ServiceException("Error retrieving a user from the database by ID: "
                        + id + ".", new Exception()));
    }

    @Override
    public void update(User user) throws ServiceException {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        userRepository.delete(user);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        Optional<User> user = Optional.ofNullable(userRepository.getUserByEmail(email));
        return user.orElseThrow(() ->
                new ServiceException("Error retrieving a user from the database by email: "
                        + email + ".", new Exception()));
    }

    @Override
    public List<User> findUsersByEmailLike(String email) throws ServiceException {
        Optional<List<User>> users = Optional.ofNullable(userRepository.findAllByEmailIsLike(email));
        return users.orElseThrow(() ->
                new ServiceException("Error retrieving users from the database by email like: "
                        + email + ".", new Exception()));
    }


    @Override
    public void userPayMethod(User user, Order order) throws ServiceException {
        try {
            user.setBalance(user.getBalance() - order.getOrderPrice());
            user.addOrder(order);
            update(user);
        } catch (ServiceException e) {
            throw new ServiceException("User update failed during purchase.", e);
        }
    }

    @Override
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
            findUser = getUserByEmail(user.getEmail());
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

    @Override
    public boolean duplicateCheck(String email) {
        try {
            User user = getUserByEmail(email);
            if (user != null) return true;
        } catch (ServiceException e) {
            log.warn("Error getting user by email: " + email + ".", e);
        }
        return false;
    }

    @Override
    public boolean validateLocalData(String date) {
        return date.matches(Pattern.compile("\\d{4}-\\d{2}-\\d{2}").pattern());
    }
}
