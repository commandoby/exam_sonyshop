package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import static com.commandoby.sonyShop.enums.PagesPathEnum.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class UserController {

    private final Logger log = LogManager.getLogger(UserController.class);
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
        ModelAndView modelAndView = new ModelAndView();

        try {
            modelAndView = userService.register(name, surname, date_of_birth, email, password, second_password);
        } catch (ServiceException e) {
            log.error(e);
        }

        return modelAndView;
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

        if (user_edit != null) modelMap.addAttribute(USER_EDIT.getValue(), user_edit);

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

        if (user_edit.equals("edit")) {
            return userService.editUserData(user, new_name, new_surname, new_date_of_birth, old_password);
        }

        if (user_edit.equals("password")) {
            return userService.editUserPassword(user, new_password, old_password, second_password);
        }

        return new ModelAndView(USER_PAGE.getPath(), new ModelMap());
    }
}
