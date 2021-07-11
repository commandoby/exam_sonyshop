package com.commandoby.sonyShop.controllers.enums;

public enum PagesPathEnum {
    HOME_PAGE("home1.jsp"),
    REGISTER_PAGE("register1.jsp"),
    SIGN_IN_PAGE("login1.jsp"),
    PRODUCTS_LIST_PAGE("products1.jsp"),
    PRODUCT_PAGE("product1.jsp"),
    BASKET_PAGE("basket1.jsp"),
    PAY_PAGE("pay1.jsp"),
    USER_PAGE("user1.jsp"),
    ADVANCED_SEARCH("advancedSearch1.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
