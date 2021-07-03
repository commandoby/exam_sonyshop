package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.SimpleSearch;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.CategoryServiceImpl;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class ProductListPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String categoryTag = servletRequest.getParameter(CATEGORY_TAG.getValue());

        try {
            Category category = searchCategory(categoryTag);
            servletRequest.setAttribute(CATEGORY_TAG.getValue(), categoryTag);
            servletRequest.setAttribute(CATEGORY_NAME.getValue(), category.getName());

            getProductList(servletRequest, category);

            String productAddName = servletRequest.getParameter(PRODUCT_NAME.getValue());
            if (productAddName != null) {
                Product product = productService.getProductByName(productAddName);
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException | ServiceException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.PRODUCTS_LIST_PAGE.getPath();
    }

    private Category searchCategory(String tag) throws NoFoundException {
        Category category = null;
        try {
            category = categoryService.getCategoryByTag(tag);
        } catch (ServiceException e) {
            log.warn(e);
        }
        return category;
    }

    private void getProductList(HttpServletRequest servletRequest, Category category) {
        List<Product> products = null;
        try {
            products = productService.getAllProductsByCategory(category);
        } catch (ServiceException e) {
            log.warn(e);
        }

        List<Product> newProductList = getSearchProductList(servletRequest, products);
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
