package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class UserPageCommandImpl implements BaseCommand {
    Logger log = Logger.getLogger(getClass());
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        try {
            User user = getUser(servletRequest);
            servletRequest.setAttribute(USER.getValue(), user);
            servletRequest.setAttribute(USER_ORDERS.getValue(), user.getOrders());
        } catch (NoFoundException e) {
            log.error(e);
            return PagesPathEnum.HOME_PAGE.getPath();
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.USER_PAGE.getPath();
    }

    public static User getUser(HttpServletRequest servletRequest) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        String email = (String) session.getAttribute(EMAIL.getValue());

        User user;
        if (email != null) {
            user = searchUser(email);
        } else {
            throw new NoFoundException("The user is missing.");
        }

        return user;
    }

    private static User searchUser(String email) throws NoFoundException {
        for (User user : ShopContent.getUserList()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new NoFoundException("User is not found.");
    }
}
