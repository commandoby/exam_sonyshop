package com.commandoby.sonyShop.controllers.enums;

public enum CommandsEnum {
    SIGN_IN_COMMAND("sign-in"),
    REGISTER_COMMAND("register"),
    HOME_PAGE_COMMAND("home_page"),
    PRODUCT_LIST_COMMAND("product_list"),
    PRODUCT_COMMAND("product"),
    BASKET_COMMAND("basket"),
    PAY_COMMAND("pay"),
    USER_COMMAND("user"),
    ADVANCED_SEARCH_COMMAND("search");

    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}