package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.UserService;
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

    private final UserService userService;
    private final CategoryService categoryService;

    public HomeController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getCategories(@ModelAttribute("order") Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        List<Category> categories = categoryService.getCategoriesSortByRating();

        if (order == null) modelMap.addAttribute(ORDER.getValue(), new Order());
        modelMap.addAttribute(CATEGORIES.getValue(), categories);
        modelMap.addAttribute(CATEGORY_MAX_RATING.getValue(), categories.get(0).getRating());
        return new ModelAndView(PagesPathEnum.HOME_PAGE.getPath(), modelMap);
    }

    @PostMapping
    public ModelAndView login(@Valid @ModelAttribute("user") User user,
            BindingResult bindingResult, ModelAndView modelAndView)
            throws ControllerException {

        if (!userService.validateUser(modelAndView, bindingResult, user)) {
            modelAndView.addObject(USER.getValue(), null);
            modelAndView.setViewName(PagesPathEnum.SIGN_IN_PAGE.getPath());
            return modelAndView;
        }

        ModelMap modelMap = new ModelMap();
        List<Category> categories = categoryService.getCategoriesSortByRating();

        modelMap.addAttribute(CATEGORIES.getValue(), categories);
        modelMap.addAttribute(CATEGORY_MAX_RATING.getValue(), categories.get(0).getRating());
        modelMap.addAttribute(ORDER.getValue(), new Order());
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName(PagesPathEnum.HOME_PAGE.getPath());
        return modelAndView;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @ModelAttribute("order")
    public Order getOrder() {
        return new Order();
    }
}
