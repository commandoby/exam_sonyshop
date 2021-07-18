package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Service
public class ProductMethodsImpl {

    private final Logger log = Logger.getLogger(getClass());
    private final ProductService productService;

    public ProductMethodsImpl(ProductService productService) {
        this.productService = productService;
    }

    public Product getProductById(int product_id) {
        Product product = null;
        try {
            product = productService.read(product_id);
        } catch (ServiceException e) {
            log.warn("Error retrieving product by ID: " + product_id + ".", e);
        }
        return product;
    }

    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = null;
        try {
            products = productService.getAllProductsByCategory(category);
        } catch (ServiceException e) {
            log.warn("Error retrieving the list of products for category: "
                    + category.getName() + ".", e);
        }
        return products;
    }

    public List<Product> getProductsByCategoryAndQuantityNotNull(Category category) {
        List<Product> products = null;
        try {
            products = productService.getProductsByCategoryAndQuantityNotNull(category);
        } catch (ServiceException e) {
            log.warn("Error retrieving the list of products for category: "
                    + category.getName() + ".", e);
        }
        return products;
    }

    public List<Product> pagination(ModelMap modelMap, List<Product> products,
                                    Integer pageItems, Integer pageNumber) {
        List<Product> productPageList = null;
        try {
            int pages = (int) Math.ceil(products.size() / pageItems.doubleValue());
            if (pageNumber > pages) pageNumber = pages;
            productPageList = getProductsPage(products, pageItems, pageNumber);
            modelMap.addAttribute(PAGE_MAX.getValue(), pages);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        } catch (NumberFormatException e) {
            log.error(e);
        }
        return productPageList;
    }

    private List<Product> getProductsPage(List<Product> products, int pageItems, int pageNumber) {
        List<Product> newProductList = new ArrayList<>();
        products.stream()
                .skip((long) pageItems * (pageNumber - 1))
                .limit(pageItems)
                .forEach(newProductList::add);
        return newProductList;
    }
}
