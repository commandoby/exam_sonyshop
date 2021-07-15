package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes("order")
public class ProductController {

    private final Logger log = Logger.getLogger(getClass().getName());
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam String category_tag) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Category category = null;
        List<Product> products = null;

        try {
            category = searchCategory(category_tag);
            products = getProductList(category);
        } catch (NoFoundException e) {
            log.error(e);
        }

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        if (products != null) modelMap.addAttribute(PRODUCT_LIST.getValue(), products);

        return new ModelAndView("products", modelMap);
    }

    @GetMapping("/product")
    public ModelAndView getProduct(@RequestParam int product_id) throws ControllerException {
        Product product = null;
        ModelMap modelMap = new ModelMap();
        try {
            product = productService.read(product_id);
        } catch (ServiceException e) {
            log.warn(e);
        }

        modelMap.addAttribute(PRODUCT.getValue(), product);

        return new ModelAndView("product", modelMap);
    }

    @PostMapping("/products")
    public ModelAndView addProducts(@RequestParam String category_tag,
                                    @RequestParam int product_id,
                                    @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Category category = null;
        List<Product> products = null;

        addProductToBasket(order, product_id);

        try {
            category = searchCategory(category_tag);
            products = getProductList(category);
        } catch (NoFoundException e) {
            log.error(e);
        }

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(ORDER.getValue(), order);
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        if (products != null) modelMap.addAttribute(PRODUCT_LIST.getValue(), products);

        return new ModelAndView("products", modelMap);
    }

    @PostMapping("/product")
    public ModelAndView addProduct(@RequestParam int product_id,
                                   @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Product product = addProductToBasket(order, product_id);

        modelMap.addAttribute(ORDER.getValue(), order);
        modelMap.addAttribute(PRODUCT.getValue(), product);

        return new ModelAndView("product", modelMap);
    }

    private Product addProductToBasket(Order order, int product_id) {
        Product product = null;
        try {
            product = productService.read(product_id);
            if (order == null) order = new Order();
            order.getProductList().add(product);
            order.setOrderPrice(order
                    .getProductList()
                    .stream()
                    .mapToInt(productOrder -> productOrder.getPrice())
                    .sum());
        } catch (ServiceException e) {
            log.warn(e);
        }
        return product;
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

    private List<Product> getProductList(Category category) {
        List<Product> products = null;
        try {
            products = productService.getAllProductsByCategory(category);
        } catch (ServiceException e) {
            log.warn(e);
        }
        return products;
    }
}
