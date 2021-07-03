package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class UserPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass());
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        try {
            User user = getUser(servletRequest);
            servletRequest.setAttribute(USER.getValue(), user);
        } catch (NoFoundException e) {
            log.error(e);
            return PagesPathEnum.HOME_PAGE.getPath();
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.USER_PAGE.getPath();
    }

    public User getUser(HttpServletRequest servletRequest) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        String email = (String) session.getAttribute(EMAIL.getValue());

        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.error(e);
        }

        return user;
    }
}
