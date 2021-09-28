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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.commandoby.sonyShop.enums.PagesPathEnum.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

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
    public ModelAndView register(String name, String surname, String date_of_birth,
                                 String email, String password, String second_password) throws ServiceException {
        ModelMap modelMap = new ModelMap();

        if (email != null) {
            if (!password.equals(second_password)) {
                modelMap.addAttribute(INFO.getValue(), "Password mismatch.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }
            if (duplicateCheck(email)) {
                modelMap.addAttribute(INFO.getValue(), "User exists.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }
            if (validateLocalData(date_of_birth)) {
                modelMap.addAttribute(INFO.getValue(), "Incorrect date format.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }

            User user = User.newBuilder()
                    .withName(name)
                    .withSurname(surname)
                    .withDateOfBirth(LocalDate.parse(date_of_birth))
                    .withEmail(email)
                    .withPassword(password)
                    .withBalance(100000).build();
            create(user);

            modelMap.addAttribute(NAME.getValue(), name);
            modelMap.addAttribute(SURNAME.getValue(), surname);
            modelMap.addAttribute(DATE_OF_BIRTH.getValue(), date_of_birth);
            modelMap.addAttribute(EMAIL.getValue(), email);
        }

        return new ModelAndView(SIGN_IN_PAGE.getPath(), modelMap);
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

    private void populateError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + "Error", bindingResult.getFieldError(field)
                    .getDefaultMessage());
        }
    }

    private boolean checkReceivedUser(ModelAndView modelAndView, User user) throws ServiceException {
        ModelMap modelMap = new ModelMap();
        User findUser = getUserByEmail(user.getEmail());
        if (findUser != null) {
            if (findUser.getPassword().equals(user.getPassword())) {
                modelMap.addAttribute(USER.getValue(), findUser);
                modelAndView.addAllObjects(modelMap);
                log.info("User " + user.getEmail() + " entered the store.");
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

    @Override
    public boolean duplicateCheck(String email) throws ServiceException {
        User user = getUserByEmail(email);
        return user != null;
    }

    @Override
    public boolean validateLocalData(String date) throws ServiceException {
        return !date.matches(Pattern.compile("\\d{4}-\\d{2}-\\d{2}").pattern());
    }

    @Override
    public ModelAndView editUserData(User user, String new_name, String new_surname,
                                     String new_date_of_birth, String old_password) throws ServiceException {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(USER_EDIT.getValue(), "edit");
        if (validateLocalData(new_date_of_birth)) {
            modelMap.addAttribute(INFO.getValue(), "Incorrect date format.");
            return new ModelAndView(USER_PAGE.getPath(), modelMap);
        }
        if (user.getPassword().equals(old_password)) {
            user.setName(new_name);
            user.setSurname(new_surname);
            user.setDateOfBirth(LocalDate.parse(new_date_of_birth));
            update(user);
            log.info("User: " + user.getEmail() + " changed the data.");
            modelMap.addAttribute(USER_EDIT.getValue(), "");
        } else {
            modelMap.addAttribute(INFO.getValue(), "Invalid password.");
        }
        return new ModelAndView(USER_PAGE.getPath(), modelMap);
    }

    @Override
    public ModelAndView editUserPassword(User user, String new_password, String old_password,
                                         String second_password) throws ServiceException {
        ModelMap modelMap = new ModelMap();
        if (!new_password.equals(second_password)) {
            modelMap.addAttribute(INFO.getValue(), "Password mismatch.");
            return new ModelAndView(USER_PAGE.getPath(), modelMap);
        }
        if (user.getPassword().equals(old_password)) {
            user.setPassword(new_password);
            update(user);
            log.info("User: " + user.getEmail() + " changed the password.");
            modelMap.addAttribute(USER_EDIT.getValue(), "");
        }
        return new ModelAndView(USER_PAGE.getPath(), modelMap);
    }
}
