package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.search.Search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.commandoby.sonyShop.enums.PagesPathEnum.HOME_PAGE;
import static com.commandoby.sonyShop.enums.PagesPathEnum.SIGN_IN_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

public class HomePageCommandImpl implements BaseCommand {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();

        String email = (String) session.getAttribute(EMAIL.getValue());
        String password = (String) session.getAttribute(PASSWORD.getValue());

        User user = User.newBuilder().withEmail(email).withPassword(password).build();
        if (validateParamNotNull(email) && validateParamNotNull(password) && checkReceivedUser(user)) {
            return categoryQuery(servletRequest);
        }

        email = servletRequest.getParameter(EMAIL.getValue());
        password = servletRequest.getParameter(PASSWORD.getValue());

        user = User.newBuilder().withEmail(email).withPassword(password).build();
        if (validateParamNotNull(email) && validateParamNotNull(password) && checkReceivedUser(user)) {
            session.setAttribute(EMAIL.getValue(), email);
            session.setAttribute(PASSWORD.getValue(), password);

            return categoryQuery(servletRequest);
        } else {
            return SIGN_IN_PAGE.getPath();
        }
    }

    private String categoryQuery(HttpServletRequest servletRequest) {
        List<Category> categoryList = getSearchCategory(servletRequest, ShopContent.getCategoriesList());
        servletRequest.setAttribute(CATEGORIES.getValue(), categoryList);

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return HOME_PAGE.getPath();
    }

    private List<Category> getSearchCategory(HttpServletRequest servletRequest, List<Category> categoryList) {
        String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
        if (searchValue != null && !searchValue.equals("")) {
            Search<Category> search = new Search<>();
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
