package com.commandoby.sonyShop.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.commandoby.sonyShop.enums.CommandsEnum.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.COMMAND;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
        COMMAND_LIST.put(PRODUCT_LIST_COMMAND.getCommand(), new ProductListPageCommandImpl());
        COMMAND_LIST.put(PRODUCT_COMMAND.getCommand(), new ProductPageCommandImpl());
        COMMAND_LIST.put(BASKET_COMMAND.getCommand(), new BasketPageCommandImpl());
        COMMAND_LIST.put(PAY_COMMAND.getCommand(), new PayCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest servletRequest) {
        String commandKey = servletRequest.getParameter(COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = SIGN_IN_COMMAND.getCommand();
        }

        return COMMAND_LIST.get(commandKey);
    }
}
