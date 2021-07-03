package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.SimpleSearch;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.CategoryServiceImpl;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class HomePageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass());
    private CategoryService categoryService = new CategoryServiceImpl();
    private UserService userService = new UserServiceImpl();

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();

        String email = servletRequest.getParameter(EMAIL.getValue());
        String password = servletRequest.getParameter(PASSWORD.getValue());
//        User user = User.newBuilder().withEmail(email).withPassword(password).build();

        if (validateParamNotNull(email) && validateParamNotNull(password)) {
            if (checkReceivedUser(email, password)) {
                session.setAttribute(EMAIL.getValue(), email);
                session.setAttribute(PASSWORD.getValue(), password);
            } else {
                session.setAttribute(EMAIL.getValue(), "");
                session.setAttribute(PASSWORD.getValue(), "");
                return PagesPathEnum.SIGN_IN_PAGE.getPath();
            }
        }

        List<Category> categoryList = getSearchCategory(servletRequest);
        servletRequest.setAttribute(CATEGORIES.getValue(), categoryList);

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.HOME_PAGE.getPath();
    }

    private List<Category> getSearchCategory(HttpServletRequest servletRequest) {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException e) {
            log.warn(e);
        }
        if (categories != null) {
            String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
            servletRequest.setAttribute(SEARCH_VALUE.getValue(), searchValue);
            if (searchValue != null && !searchValue.equals("")) {
                SimpleSearch<Category> search = new SimpleSearch<>();
                return search.searchName(searchValue, categories);
            }
        }
        return categories;
    }

    private boolean checkReceivedUser(String email, String password) {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.info(e);
        }
        if (user != null)
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        return false;
    }

    private boolean validateParamNotNull(String parameter) {
        return parameter != null && !parameter.equals("");
    }
}
