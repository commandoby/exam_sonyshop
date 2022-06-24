package com.commandoby.sonyShop.enums;

public enum RequestParamEnum {
    INFO("info"),
    NAME("name"),
    SURNAME("surname"),
    DATE_OF_BIRTH("date_of_birth"),
    EMAIL("email"),
    USER("user"),
    USER_EDIT("user_edit"),
    CATEGORIES("categories"),
    CATEGORY_NAME("category_name"),
    CATEGORY_TAG("category_tag"),
    CATEGORY_MAX_RATING("category_max_rating"),
    PRODUCT("product"),
    PRODUCT_LIST("product_list"),
    PRODUCT_SIZE("product_size"),
    IS_QUANTITY("is_quantity"),
    ORDER("order"),
    BASKET_SIZE("basket_size"),
    BASKET_PRICE("basket_price"),
    SEARCH_VALUE("search_value"),
    SEARCH_COMPARING("search_comparing"),
    MIN_PRICE("min_price"),
    MAX_PRICE("max_price"),
    FOUND_ITEMS("found_items"),
    PAGE_ITEMS("page_items"),
    PAGE_NUMBER("page_number");
    //PAGE_MAX("page_max");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
