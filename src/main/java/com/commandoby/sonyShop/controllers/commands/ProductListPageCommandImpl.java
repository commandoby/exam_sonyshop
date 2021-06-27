package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.SimpleSearch;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class ProductListPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String categoryTag = servletRequest.getParameter(CATEGORY_TAG.getValue());

        try {
            servletRequest.setAttribute(CATEGORY_TAG.getValue(), categoryTag);
            servletRequest.setAttribute(CATEGORY_NAME.getValue(), searchCategoryName(categoryTag));

            getProductList(servletRequest, categoryTag);

            String productAddName = servletRequest.getParameter(PRODUCT_NAME.getValue());
            if (productAddName != null) {
                Product product = ShopContent.getProduct(productAddName);
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.PRODUCTS_LIST_PAGE.getPath();
    }

    private String searchCategoryName(String tag) throws NoFoundException {
        for (Category category : ShopContent.getCategoriesList()) {
            if (category.getTag().equals(tag)) return category.getName();
        }
        throw new NoFoundException("Category: " + tag + " not found.");
    }

    private void getProductList(HttpServletRequest servletRequest, String tag) {
        List<Product> productList = new ArrayList<>();
        for (Product product : ShopContent.getProductList()) {
            if (product.getCategory().getTag().equals(tag)) productList.add(product);
        }

        List<Product> newProductList = getSearchProductList(servletRequest, productList);
        servletRequest.setAttribute(PRODUCT_LIST.getValue(), newProductList);
        servletRequest.setAttribute(PRODUCT_SIZE.getValue(), newProductList.size());
    }

    private List<Product> getSearchProductList(HttpServletRequest servletRequest, List<Product> productList) {
        String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
        servletRequest.setAttribute(SEARCH_VALUE.getValue(), searchValue);
        if (searchValue != null && !searchValue.equals("")) {
            SimpleSearch<Product> search = new SimpleSearch<>();
            return search.searchName(searchValue, productList);
        }
        return productList;
    }
}
