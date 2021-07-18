package com.commandoby.sonyShop.controllers.enums;

public enum RequestParamEnum {
    INFO("info"),
    NAME("name"),
    SURNAME("surname"),
    DATE_OF_BIRTH("date_of_birth"),
    EMAIL("email"),
    PASSWORD("password"),
    SECOND_PASSWORD("second_password"),
    USER("user"),
    USER_BALANCE("user_balance"),
    USER_ORDERS("user_orders"),
    COMMAND("command"),
    CATEGORIES("categories"),
    CATEGORY_NAME("category_name"),
    CATEGORY_TAG("category_tag"),
    PRODUCT("product"),
    PRODUCT_LIST("product_list"),
    PRODUCT_SIZE("product_size"),
    PRODUCT_NAME("product_name"),
    PRODUCT_NAME_OUT_OF_PRODUCT("product_name_out"),
    ORDER("order"),
    BASKET_SIZE("basket_size"),
    BASKET_PRICE("basket_price"),
    REMOVE_PRODUCT_ID("remove_id"),
    SEARCH_VALUE("search_value"),
    SEARCH_CATEGORY("search_category"),
    MIN_PRICE("min_price"),
    MAX_PRICE("max_price"),
    PAGE_ITEMS("page_items");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
