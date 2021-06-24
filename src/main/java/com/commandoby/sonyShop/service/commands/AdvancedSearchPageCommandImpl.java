package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;

import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class AdvancedSearchPageCommandImpl implements BaseCommand{
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {

        servletRequest.setAttribute(CATEGORIES.getValue(), ShopContent.getCategoriesList());

        return PagesPathEnum.ADVANCED_SEARCH.getPath();
    }
}
