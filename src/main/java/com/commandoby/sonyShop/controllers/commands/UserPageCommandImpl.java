package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class UserPageCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
            User user = (User) session.getAttribute(USER.getValue());
            if (user != null) {
                servletRequest.setAttribute(USER.getValue(), user);
            } else {
                return PagesPathEnum.HOME_PAGE.getPath();
            }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.USER_PAGE.getPath();
    }
}
