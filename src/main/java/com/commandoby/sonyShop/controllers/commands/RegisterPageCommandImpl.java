package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;

import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class RegisterPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String email = servletRequest.getParameter(EMAIL.getValue());
        String password = servletRequest.getParameter(PASSWORD.getValue());

        servletRequest.setAttribute(EMAIL.getValue(), email);
        servletRequest.setAttribute(PASSWORD.getValue(), password);

        return PagesPathEnum.REGISTER_PAGE.getPath();
    }
}
