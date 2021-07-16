package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest servletRequest) throws CommandException;
}
