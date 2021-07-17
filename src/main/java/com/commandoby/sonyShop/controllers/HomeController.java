package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.impl.UserValidateImpl;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@RestController
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class HomeController {

    private final Logger log = Logger.getLogger(getClass());
    private final CategoryService categoryService;
    private final UserValidateImpl userValidate;

    public HomeController(CategoryService categoryService, UserValidateImpl userValidate) {
        this.categoryService = categoryService;
        this.userValidate = userValidate;
    }

    @GetMapping
    public ModelAndView getCategories() throws ControllerException {
        ModelMap modelMap = new ModelMap();

        List<Category> categoryList = getCategory();

        modelMap.addAttribute(CATEGORIES.getValue(), categoryList);
        if (modelMap.getAttribute(ORDER.getValue()) == null) {
            modelMap.addAttribute(ORDER.getValue(), new Order());
        }

        return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);
    }

    @PostMapping
    public ModelAndView login(@Valid @ModelAttribute("user") User user,
            BindingResult bindingResult, ModelAndView modelAndView)
            throws ControllerException {

        if (!userValidate.validateUser(modelAndView, bindingResult, user)) {
            modelAndView.addObject(USER.getValue(), null);
            modelAndView.setViewName(PagesPathEnum.SIGN_IN_PAGE.getPath());
            return modelAndView;
        }

        ModelMap modelMap = new ModelMap();

        List<Category> categoryList = getCategory();

        modelMap.addAttribute(CATEGORIES.getValue(), categoryList);
        modelMap.addAttribute(ORDER.getValue(), new Order());
        modelAndView.setViewName(PagesPathEnum.HOME_PAGE.getPath());
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    private List<Category> getCategory() {
        List<Category> categories = null;
        try {
            categories = categoryService.gelAllCategorySortByRating();
        } catch (ServiceException e) {
            log.warn(e);
        }
        return categories;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
