package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

import static com.commandoby.sonyShop.enums.PagesPathEnum.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class UserController {

    private final Logger log = Logger.getLogger(getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public ModelAndView signIn(SessionStatus sessionStatus) throws ControllerException {
        sessionStatus.setComplete();

        return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath(), new ModelMap());
    }

    @PostMapping("/signin")
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String date_of_birth,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String second_password) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (email != null) {
            if (!password.equals(second_password)) {
                modelMap.addAttribute(INFO.getValue(), "Password mismatch.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }
            if (userService.duplicateCheck(email)) {
                modelMap.addAttribute(INFO.getValue(), "User exists.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }
            if (!userService.validateLocalData(date_of_birth)) {
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
            try {
                userService.create(user);
            } catch (ServiceException e) {
                log.warn(e);
            }

            modelMap.addAttribute(NAME.getValue(), name);
            modelMap.addAttribute(SURNAME.getValue(), surname);
            modelMap.addAttribute(DATE_OF_BIRTH.getValue(), date_of_birth);
            modelMap.addAttribute(EMAIL.getValue(), email);
        }

        return new ModelAndView(SIGN_IN_PAGE.getPath(), modelMap);
    }

    @GetMapping("/new")
    public ModelAndView newUser(@RequestParam(required = false) String email) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute(EMAIL.getValue(), email);
        return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
    }

    @GetMapping("/user")
    public ModelAndView user(@RequestParam String email,
                             @RequestParam(required = false) String user_edit,
                             @ModelAttribute User user) throws ControllerException {
        if (user == null || !email.equals(user.getEmail())) {
            return new ModelAndView(HOME_PAGE.getPath(), new ModelMap());
        }

        ModelMap modelMap = new ModelMap();

        if (user_edit != null) {
            if (user_edit.equals("edit")) modelMap.addAttribute(USER_EDIT.getValue(), "edit");
            if (user_edit.equals("password")) modelMap.addAttribute(USER_EDIT.getValue(), "password");
        }
        return new ModelAndView(USER_PAGE.getPath(), modelMap);
    }

    @PostMapping("/user")
    public ModelAndView edit(@RequestParam(required = false) String new_name,
                             @RequestParam(required = false) String new_surname,
                             @RequestParam(required = false) String new_date_of_birth,
                             @RequestParam String old_password,
                             @RequestParam(required = false) String new_password,
                             @RequestParam(required = false) String second_password,
                             @RequestParam(required = false) String user_edit,
                             @ModelAttribute User user) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (user_edit.equals("edit")) {
            modelMap.addAttribute(USER_EDIT.getValue(), "edit");
            if (!userService.validateLocalData(new_date_of_birth)) {
                modelMap.addAttribute(INFO.getValue(), "Incorrect date format.");
                return new ModelAndView(USER_PAGE.getPath(), modelMap);
            }
            if (user.getPassword().equals(old_password)) {
                try {
                    user.setName(new_name);
                    user.setSurname(new_surname);
                    user.setDateOfBirth(LocalDate.parse(new_date_of_birth));
                    userService.update(user);
                    log.info("User: " + user.getEmail() + " changed the data.");
                } catch (ServiceException e) {
                    log.error("Error updating user data. Email" + user.getEmail(), e);
                }
            } else {
                modelMap.addAttribute(INFO.getValue(), "Invalid password.");
                return new ModelAndView(USER_PAGE.getPath(), modelMap);
            }
        }

        if (user_edit.equals("password")) {
            if (!new_password.equals(second_password)) {
                modelMap.addAttribute(INFO.getValue(), "Password mismatch.");
                return new ModelAndView(USER_PAGE.getPath(), modelMap);
            }
            if (user.getPassword().equals(old_password)) {
                try {
                    user.setPassword(new_password);
                    userService.update(user);
                    log.info("User: " + user.getEmail() + " changed the password.");
                } catch (ServiceException e) {
                    log.error("Error updating user password. Email" + user.getEmail(), e);
                }
            }
        }

        modelMap.addAttribute(USER_EDIT.getValue(), "");
        return new ModelAndView(USER_PAGE.getPath(), modelMap);
    }
}
