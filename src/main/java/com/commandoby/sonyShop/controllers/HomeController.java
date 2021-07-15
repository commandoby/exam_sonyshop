package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

@RestController
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class HomeController {

    private final Logger log = Logger.getLogger(getClass());
    private final CategoryService categoryService;
    private final UserService userService;

    public HomeController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getCategories() throws ControllerException {
        ModelMap modelMap = new ModelMap();

        List<Category> categoryList = getCategory();

        modelMap.addAttribute(CATEGORIES.getValue(), categoryList);
        if (modelMap.getAttribute(ORDER.getValue()) == null) {
            modelMap.addAttribute(ORDER.getValue(), new Order());
        }

        return new ModelAndView("home", modelMap);
    }

    @PostMapping
    public ModelAndView login(@RequestParam String email, @RequestParam String password)
            throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (validateParamNotNull(email) && validateParamNotNull(password)
                && !checkReceivedUser(modelMap, email, password)) {
            return new ModelAndView("login", modelMap);
        }

        List<Category> categoryList = getCategory();

        modelMap.addAttribute(CATEGORIES.getValue(), categoryList);
        modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView("home", modelMap);
    }

    private List<Category> getCategory() {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException e) {
            log.warn(e);
        }
        return categories;
    }

    private boolean checkReceivedUser(ModelMap modelMap, String email, String password) {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.info(e);
        }
        if (user != null) {
            if (user.getPassword().equals(password)) {
                modelMap.addAttribute(USER.getValue(), user);
                return true;
            } else {
                modelMap.addAttribute(INFO.getValue(), "Invalid password.");
            }
        } else {
            modelMap.addAttribute(INFO.getValue(), "User is not found.");
        }
        modelMap.addAttribute(USER.getValue(), null);
        return false;
    }

    private boolean validateParamNotNull(String parameter) {
        return parameter != null && !parameter.equals("");
    }
}
