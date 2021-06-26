package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest servletRequest) throws CommandException;
}