package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.repository.domain.User;
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
    public ModelAndView signIn(SessionStatus sessionStatus) throws CommandException {
        sessionStatus.setComplete();

        return new ModelAndView(PagesPathEnum.SIGN_IN_PAGE.getPath(), new ModelMap());
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
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
            }

            if (duplicateCheck(email)) {
                modelMap.addAttribute(INFO.getValue(), "User exists");
                return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
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

        return new ModelAndView(SIGN_IN_PAGE.getPath(), modelMap);
    }

    @GetMapping("/new")
    public ModelAndView newUser(@RequestParam(required = false) String email) throws CommandException {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute(EMAIL.getValue(), email);

        return new ModelAndView(REGISTER_PAGE.getPath(), modelMap);
    }

    @GetMapping("/user")
    public ModelAndView execute(@RequestParam String email,
                                @ModelAttribute User user) throws CommandException {
        if (user == null || !email.equals(user.getEmail())) {
            return new ModelAndView(HOME_PAGE.getPath(), new ModelMap());
        }

        return new ModelAndView(USER_PAGE.getPath(), new ModelMap());
    }

    private boolean duplicateCheck(String email) {
        try {
            /*List<String> emails = userService.getAllUsersEmails();
            for (String s : emails) if (s.equals(email)) return true;*/
            User user = userService.getUserByEmail(email);
            if (user != null) return true;
        } catch (ServiceException e) {
            log.warn(e);
        }
        return false;
    }
}
