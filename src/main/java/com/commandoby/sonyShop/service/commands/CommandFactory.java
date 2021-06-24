package com.commandoby.sonyShop.service.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.commandoby.sonyShop.service.enums.CommandsEnum.*;
import static com.commandoby.sonyShop.service.enums.RequestParamEnum.COMMAND;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(REGISTER_COMMAND.getCommand(), new RegisterPageCommandImpl());
        COMMAND_LIST.put(HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
        COMMAND_LIST.put(PRODUCT_LIST_COMMAND.getCommand(), new ProductListPageCommandImpl());
        COMMAND_LIST.put(PRODUCT_COMMAND.getCommand(), new ProductPageCommandImpl());
        COMMAND_LIST.put(BASKET_COMMAND.getCommand(), new BasketPageCommandImpl());
        COMMAND_LIST.put(PAY_COMMAND.getCommand(), new PayPageCommandImpl());
        COMMAND_LIST.put(USER_COMMAND.getCommand(), new UserPageCommandImpl());
        COMMAND_LIST.put(ADVANCED_SEARCH_COMMAND.getCommand(), new AdvancedSearchPageCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest servletRequest) {
        String commandKey = servletRequest.getParameter(COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = HOME_PAGE_COMMAND.getCommand();
        }

        return COMMAND_LIST.get(commandKey);
    }
}
