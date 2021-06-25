package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;
import com.commandoby.sonyShop.service.search.SimpleSearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class HomePageCommandImpl implements BaseCommand {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();

        String email = servletRequest.getParameter(EMAIL.getValue());
        String password = servletRequest.getParameter(PASSWORD.getValue());
        User user = User.newBuilder().withEmail(email).withPassword(password).build();

        if (validateParamNotNull(email) && validateParamNotNull(password) && checkReceivedUser(user)) {
            session.setAttribute(EMAIL.getValue(), email);
            session.setAttribute(PASSWORD.getValue(), password);
        }

        List<Category> categoryList = getSearchCategory(servletRequest);
        servletRequest.setAttribute(CATEGORIES.getValue(), categoryList);

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.HOME_PAGE.getPath();
    }

    private List<Category> getSearchCategory(HttpServletRequest servletRequest) {
        List<Category> categoryList = ShopContent.getCategoriesList();
        String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
        servletRequest.setAttribute(SEARCH_VALUE.getValue(), searchValue);
        if (searchValue != null && !searchValue.equals("")) {
            SimpleSearch<Category> search = new SimpleSearch<>();
            return search.searchName(searchValue, categoryList);
        }
        return categoryList;
    }

    private boolean checkReceivedUser(User user) {
        if (user != null) {
            for (User u : ShopContent.getUserList()) {
                if (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword()))
                    return true;
            }
        }
        return false;
    }

    private boolean validateParamNotNull(String parameter) {
        if (parameter != null && parameter != "") return true;
        return false;
    }
}
