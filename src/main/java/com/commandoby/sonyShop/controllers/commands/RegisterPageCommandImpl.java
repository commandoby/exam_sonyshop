package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;

import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class RegisterPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String email = servletRequest.getParameter(EMAIL.getValue());

        servletRequest.setAttribute(EMAIL.getValue(), email);

        return PagesPathEnum.REGISTER_PAGE.getPath();
    }
}
