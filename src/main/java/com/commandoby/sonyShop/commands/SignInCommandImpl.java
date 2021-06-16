package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.enums.PagesPathEnum.SIGN_IN_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

public class SignInCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
        session.setAttribute(EMAIL.getValue(), "");
        session.setAttribute(PASSWORD.getValue(), "");
        return SIGN_IN_PAGE.getPath();
    }
}
