package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.AdvancedSearch;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class AdvancedSearchPageCommandImpl implements BaseCommand {
    Logger log = Logger.getLogger(getClass());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
        String pageItems = servletRequest.getParameter(PAGE_ITEMS.getValue());
        String searchCategory = servletRequest.getParameter("search_category");
        if (pageItems == null || pageItems == "") pageItems = "all";
        Integer minPrice = getMinPrice(servletRequest);
        Integer maxPrice = getMaxPrice(servletRequest, minPrice);
        List<Product> productList = AdvancedSearch.search(searchValue, minPrice, maxPrice, searchCategory);

        try {
            String productAddName = servletRequest.getParameter(PRODUCT_NAME.getValue());
            if (productAddName != null) {
                Product product = ShopContent.getProduct(productAddName);
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);

        servletRequest.setAttribute(SEARCH_VALUE.getValue(), searchValue);
        servletRequest.setAttribute(CATEGORIES.getValue(), ShopContent.getCategoriesList());
        servletRequest.setAttribute(PAGE_ITEMS.getValue(), pageItems);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(MIN_PRICE.getValue(), minPrice);
        servletRequest.setAttribute(MAX_PRICE.getValue(), maxPrice);
        servletRequest.setAttribute(PRODUCT_LIST.getValue(), productList);
        servletRequest.setAttribute(PRODUCT_SIZE.getValue(), productList.size());

        return PagesPathEnum.ADVANCED_SEARCH.getPath();
    }

    private Integer getMinPrice(HttpServletRequest servletRequest) {
        Integer minPrice = null;
        String minString = servletRequest.getParameter(MIN_PRICE.getValue());
        try {
            if (minString != null && !minString.equals("")) minPrice = Integer.parseInt(minString);
        } catch (NumberFormatException e) {
            log.warn(e);
        }
        if (minPrice != null) if (minPrice < 0) minPrice = null;
        return minPrice;
    }

    private Integer getMaxPrice(HttpServletRequest servletRequest, Integer minPrice) {
        Integer maxPrice = null;
        String maxString = servletRequest.getParameter(MAX_PRICE.getValue());
        try {
            if (maxString != null && !maxString.equals("")) maxPrice = Integer.parseInt(maxString);
        } catch (NumberFormatException e) {
            log.warn(e);
        }
        if (maxPrice != null && maxPrice < 0) maxPrice = null;
        if (maxPrice != null && minPrice != null && maxPrice < minPrice) maxPrice = minPrice;
        return maxPrice;
    }
}
