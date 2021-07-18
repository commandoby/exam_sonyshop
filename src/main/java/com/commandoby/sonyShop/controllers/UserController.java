package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserValidateImpl;
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
    private final UserValidateImpl userValidate;

    public UserController(UserService userService, UserValidateImpl userValidate) {
        this.userService = userService;
        this.userValidate = userValidate;
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
            if (userValidate.duplicateCheck(email)) {
                modelMap.addAttribute(INFO.getValue(), "User exists.");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }
            if (!userValidate.validateLocalData(date_of_birth)) {
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
    public ModelAndView execute(@RequestParam String email,
                                @ModelAttribute User user) throws ControllerException {
        if (user == null || !email.equals(user.getEmail())) {
            return new ModelAndView(HOME_PAGE.getPath(), new ModelMap());
        }

        return new ModelAndView(USER_PAGE.getPath(), new ModelMap());
    }
}
