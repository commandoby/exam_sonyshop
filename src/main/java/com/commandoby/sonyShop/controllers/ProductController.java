package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.controllers.search.SimpleSearch;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
public class ProductController {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam String category_tag,
                                    @RequestParam(required = false) String search_value) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Category category = null;
        List<Product> products = null;

        try {
            category = searchCategory(category_tag);
            products = getProductList(category, search_value);

            /*String productAddName = servletRequest.getParameter(PRODUCT_NAME.getValue());
            if (productAddName != null) {
                Product product = productService.getProductByName(productAddName);
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }*/
        } catch (NoFoundException e) {
            log.error(e);
        }

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(SEARCH_VALUE.getValue(), search_value);
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        if (products != null) {
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products);
            modelMap.addAttribute(PRODUCT_SIZE.getValue(), products.size());
        }

        return new ModelAndView("products", modelMap);
    }

    @GetMapping("/product")
    public ModelAndView getProduct(@RequestParam int id) throws ControllerException {
        Product product = null;
        ModelMap modelMap = new ModelMap();
        try {
            product = productService.read(id);
//            servletRequest.setAttribute(PRODUCT.getValue(), product);
            /*if (servletRequest.getParameter(PRODUCT_NAME_OUT_OF_PRODUCT.getValue()) != null) {
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }*/
        } /*catch (NoFoundException e) {
            log.error(e);
        }*/ catch (ServiceException e) {
            log.warn(e);
        }

        modelMap.addAttribute(PRODUCT.getValue(), product);

        return new ModelAndView("product", modelMap);
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

    private List<Product> getProductList(Category category, String searchValue) {
        List<Product> products = null;
        try {
            products = productService.getAllProductsByCategory(category);
        } catch (ServiceException e) {
            log.warn(e);
        }

        List<Product> newProductList = getSearchProductList(products, searchValue);
//        servletRequest.setAttribute(PRODUCT_LIST.getValue(), newProductList);
//        servletRequest.setAttribute(PRODUCT_SIZE.getValue(), newProductList.size());
        return newProductList;
    }

    private List<Product> getSearchProductList(List<Product> productList, String searchValue) {
        if (searchValue != null && !searchValue.equals("")) {
            SimpleSearch<Product> search = new SimpleSearch<>();
            return search.searchName(searchValue, productList);
        }
        return productList;
    }
}
