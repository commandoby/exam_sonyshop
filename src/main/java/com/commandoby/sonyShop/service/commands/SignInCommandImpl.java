package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class SignInCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
        session.setAttribute(EMAIL.getValue(), "");
        session.setAttribute(PASSWORD.getValue(), "");

        if (servletRequest.getParameter(EMAIL.getValue()) != null) {
            if (!secondPasswordCheck(servletRequest)) {
                servletRequest.setAttribute(INFO.getValue(), "Password mismatch");
                return PagesPathEnum.REGISTER_PAGE.getPath();
            }

            if (duplicateCheck(servletRequest)) {
                servletRequest.setAttribute(INFO.getValue(), "User exists");
                return PagesPathEnum.REGISTER_PAGE.getPath();
            }

            String name = servletRequest.getParameter(NAME.getValue());
            String surname = servletRequest.getParameter(SURNAME.getValue());
            String data = servletRequest.getParameter(USER_DATA.getValue());
            String email = servletRequest.getParameter(EMAIL.getValue());
            String password = servletRequest.getParameter(PASSWORD.getValue());
            ShopContent.setUser(new User(name, surname, email, password, data, 100000));
        }

        return PagesPathEnum.SIGN_IN_PAGE.getPath();
    }

    private boolean secondPasswordCheck(HttpServletRequest servletRequest) {
        String password = servletRequest.getParameter(PASSWORD.getValue());
        String secondPassword = servletRequest.getParameter(SECOND_PASSWORD.getValue());
        if (password.equals(secondPassword)) return true;
        return false;
    }

    private boolean duplicateCheck(HttpServletRequest servletRequest) {
        String email = servletRequest.getParameter(EMAIL.getValue());
        for (User user : ShopContent.getUserList()) {
            if (user.getEmail().equals(email)) return true;
        }
        return false;
    }
}
