package com.commandoby.sonyShop.enums;

public enum PagesPathEnum {
    HOME_PAGE("home"),
    REGISTER_PAGE("register"),
    SIGN_IN_PAGE("login"),
    PRODUCT_LIST_PAGE("products"),
    PRODUCT_PAGE("product"),
    BASKET_PAGE("basket"),
    PAY_PAGE("pay"),
    USER_PAGE("user"),
    ADVANCED_SEARCH("advancedSearch");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
