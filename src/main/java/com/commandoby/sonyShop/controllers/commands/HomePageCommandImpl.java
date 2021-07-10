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
import com.commandoby.sonyShop.utills.DataSourceHolder;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class HomePageCommandImpl implements BaseCommand {
    private final EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    private final Logger log = Logger.getLogger(getClass());
    private final CategoryService categoryService = new CategoryServiceImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager);

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {

        String email = servletRequest.getParameter(EMAIL.getValue());
        String password = servletRequest.getParameter(PASSWORD.getValue());
        servletRequest.setAttribute(EMAIL.getValue(), email);

        if (validateParamNotNull(email) && validateParamNotNull(password)
                && !checkReceivedUser(servletRequest, email, password))
            return PagesPathEnum.SIGN_IN_PAGE.getPath();

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

    private boolean checkReceivedUser(HttpServletRequest servletRequest, String email, String password) {
        HttpSession session = servletRequest.getSession();
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.info(e);
        }
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute(USER.getValue(), user);
                return true;
            } else {
                servletRequest.setAttribute(INFO.getValue(), "Invalid password.");
            }
        } else {
            servletRequest.setAttribute(INFO.getValue(), "User is not found.");
        }
        session.setAttribute(USER.getValue(), null);
        return false;
    }

    private boolean validateParamNotNull(String parameter) {
        return parameter != null && !parameter.equals("");
    }
}
