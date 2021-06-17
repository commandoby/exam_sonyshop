package com.commandoby.sonyShop.enums;

public enum RequestParamEnum {
    INFO("info"),
    NAME("name"),
    SURNAME("surname"),
    USER_DATA("data"),
    EMAIL("email"),
    PASSWORD("password"),
    SECOND_PASSWORD("second_password"),
    COMMAND("command"),
    CATEGORIES("categories"),
    CATEGORY_NAME("category_name"),
    CATEGORY_TAG("category_tag"),
    PRODUCT("product"),
    PRODUCT_LIST("product_list"),
    PRODUCT_SIZE("product_size"),
    PRODUCT_NAME("product_name"),
    PRODUCT_NAME_OUT_OF_PRODUCT("product_name_out"),
    BASKET("basket"),
    BASKET_SIZE("basket_size"),
    BASKET_PRICE("basket_price"),
    REMOVE_PRODUCT_ID("remove_id"),
    SEARCH_VALUE("search_value");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
