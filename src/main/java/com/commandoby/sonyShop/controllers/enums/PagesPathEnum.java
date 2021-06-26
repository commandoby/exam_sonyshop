package com.commandoby.sonyShop.controllers.enums;

public enum PagesPathEnum {
    HOME_PAGE("home.jsp"),
    REGISTER_PAGE("register.jsp"),
    SIGN_IN_PAGE("login.jsp"),
    PRODUCTS_LIST_PAGE("products.jsp"),
    PRODUCT_PAGE("product.jsp"),
    BASKET_PAGE("basket.jsp"),
    PAY_PAGE("pay.jsp"),
    USER_PAGE("user.jsp"),
    ADVANCED_SEARCH("advancedSearch.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
