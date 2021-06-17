package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;
import static com.commandoby.sonyShop.enums.PagesPathEnum.REGISTER_PAGE;

public class RegisterPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String email = servletRequest.getParameter(EMAIL.getValue());
        String password = servletRequest.getParameter(PASSWORD.getValue());

        servletRequest.setAttribute(EMAIL.getValue(), email);
        servletRequest.setAttribute(PASSWORD.getValue(), password);

        return REGISTER_PAGE.getPath();
    }
}
