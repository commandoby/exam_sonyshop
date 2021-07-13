package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

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
    public ModelAndView signIn(SessionStatus sessionStatus) throws CommandException {
        sessionStatus.setComplete();

        return new ModelAndView("login", new ModelMap());
    }

    @PostMapping("/signin")
    public ModelAndView register(@RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String date_of_birth,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String second_password) throws CommandException {
        ModelMap modelMap = new ModelMap();

        if (email != null) {
            modelMap.addAttribute(NAME.getValue(), name);
            modelMap.addAttribute(SURNAME.getValue(), surname);
            modelMap.addAttribute(DATE_OF_BIRTH.getValue(), date_of_birth);
            modelMap.addAttribute(EMAIL.getValue(), email);

            if (!password.equals(second_password)) {
                modelMap.addAttribute(INFO.getValue(), "Password mismatch");
                return new ModelAndView("register", modelMap);
            }

            if (duplicateCheck(email)) {
                modelMap.addAttribute(INFO.getValue(), "User exists");
                return new ModelAndView("register", modelMap);
            }

            User user = User.newBuilder()
                    .withName(name)
                    .withSurname(surname)
                    .withDateOfBirth(date_of_birth)
                    .withEmail(email)
                    .withPassword(password)
                    .withBalance(100000).build();
            try {
                userService.create(user);
            } catch (ServiceException e) {
                log.warn(e);
            }
        }

        return new ModelAndView("login", modelMap);
    }

    @GetMapping("/new")
    public ModelAndView newUser(@RequestParam(required = false) String email) throws CommandException {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute(EMAIL.getValue(), email);

        return new ModelAndView("register", modelMap);
    }

    @GetMapping("/user")
    public ModelAndView execute(@RequestParam String email,
                                @ModelAttribute User user) throws CommandException {
//        ModelMap modelMap = new ModelMap();
        if (user == null || !email.equals(user.getEmail())) {
            return new ModelAndView("home", new ModelMap());
        }

        return new ModelAndView("user", new ModelMap());
    }

    private boolean duplicateCheck(String email) {
        try {
            List<String> emails = userService.getAllUsersEmails();
            for (String s : emails) if (s.equals(email)) return true;
        } catch (ServiceException e) {
            log.warn(e);
        }
        return false;
    }
}
