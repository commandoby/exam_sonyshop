package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.dao.impl.ProductDaoImpl;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.AdvancedSearch;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.CategoryServiceImpl;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import com.commandoby.sonyShop.utills.DataSourceHolder;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class AdvancedSearchPageCommandImpl implements BaseCommand {
    private final EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

    private final Logger log = Logger.getLogger(getClass());
    private final AdvancedSearch advancedSearch = new AdvancedSearch(new ProductServiceImpl(new ProductDaoImpl()));
    private final ProductService productService = new ProductServiceImpl(new ProductDaoImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String searchValue = servletRequest.getParameter(SEARCH_VALUE.getValue());
        String searchCategory = servletRequest.getParameter(SEARCH_CATEGORY.getValue());
        String searchComparing = servletRequest.getParameter(SEARCH_COMPARING.getValue());
        String pageItems = servletRequest.getParameter(PAGE_ITEMS.getValue());
        String pageNumber = servletRequest.getParameter(PAGE_NUMBER.getValue());
        Integer minPrice = getMinPrice(servletRequest);
        Integer maxPrice = getMaxPrice(servletRequest, minPrice);
        List<Category> categories = getCategories();
        List<Product> products = new ArrayList<>();
        List<List<Product>> productPagesList;

        if (searchCategory == null || searchCategory.equals("")) searchCategory = "";
        if (searchComparing == null || searchComparing.equals("")) searchComparing = "Price +";
        if (!searchValue.equals("") || minPrice != null || maxPrice != null)
            products = advancedSearch.search(searchValue, minPrice, maxPrice, searchCategory, searchComparing);
        addBasketProduct(servletRequest);
        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);

        if (pageItems == null || pageItems.equals("")) pageItems = "0";
        if (pageNumber == null || pageNumber.equals("")) pageNumber = "1";
        if (!pageItems.equals("0") && !products.isEmpty()) {
            int pageNumberInt = 1;
            try {
                Integer pageItemsInt = Integer.parseInt(pageItems);
                productPagesList = getProductPagesList(products, pageItemsInt);
                pageNumberInt = Integer.parseInt(pageNumber);
                int pages = (int) Math.ceil(products.size() / pageItemsInt.doubleValue());
                if (pageNumberInt > pages) pageNumberInt = pages;
                servletRequest.setAttribute(PRODUCT_LIST.getValue(), productPagesList.get(pageNumberInt - 1));
            } catch (NumberFormatException e) {
                log.error(e);
            }
            servletRequest.setAttribute(PAGE_NUMBER.getValue(), pageNumberInt);
        } else {
            servletRequest.setAttribute(PRODUCT_LIST.getValue(), products);
            servletRequest.setAttribute(PAGE_NUMBER.getValue(), "1");
        }

        servletRequest.setAttribute(SEARCH_VALUE.getValue(), searchValue);
        servletRequest.setAttribute(SEARCH_CATEGORY.getValue(), searchCategory);
        servletRequest.setAttribute(SEARCH_COMPARING.getValue(), searchComparing);
        servletRequest.setAttribute(CATEGORIES.getValue(), categories);
        servletRequest.setAttribute(PAGE_ITEMS.getValue(), pageItems);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(MIN_PRICE.getValue(), minPrice);
        servletRequest.setAttribute(MAX_PRICE.getValue(), maxPrice);
        servletRequest.setAttribute(PRODUCT_SIZE.getValue(), products.size());

        return PagesPathEnum.ADVANCED_SEARCH.getPath();
    }

    private List<List<Product>> getProductPagesList(List<Product> products, Integer pageItemsInt)
            throws NumberFormatException {
        List<List<Product>> productPagesList = new ArrayList<>();
        int pages = (int) Math.ceil(products.size() / pageItemsInt.doubleValue());
        for (int i = 0; i < pages; i++) {
            List<Product> products1 = new ArrayList<>();
            for (int j = 0; j < pageItemsInt; j++) {
                if (pageItemsInt * i + j < products.size())
                    products1.add(products.get(pageItemsInt * i + j));
            }
            productPagesList.add(products1);
        }
        return productPagesList;
    }

    private List<Category> getCategories() {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException e) {
            log.warn(e);
        }
        return categories;
    }

    private void addBasketProduct(HttpServletRequest servletRequest) {
        try {
            String productAddName = servletRequest.getParameter(PRODUCT_NAME.getValue());
            if (productAddName != null) {
                Product product = productService.getProductByName(productAddName);
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException | ServiceException e) {
            log.error(e);
        }
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
