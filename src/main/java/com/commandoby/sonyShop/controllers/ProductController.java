package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes("order")
public class ProductController {

    private final Logger log = LogManager.getLogger(ProductController.class);
    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;

    public ProductController(CategoryService categoryService,
                             ProductService productService, OrderService orderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public ModelAndView getProductsByCategory(@RequestParam String category_tag,
                                              @RequestParam(required = false) Integer page_items,
                                              @RequestParam(required = false) Integer page_number) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        try {
            Category category = categoryService.getCategoryForProducts(category_tag);
            long found_items = productService.countAllProductsByCategory(category);
            
            if (page_items == null) page_items = 5;
            if (page_number == null) page_number = 1;
            if (page_items * page_number > found_items) page_number = (int) found_items / page_items;
            Page<Product> products = productService.getProductsByCategoryNotNull(category, PageRequest.of(page_number - 1, page_items));

            modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
            modelMap.addAttribute(FOUND_ITEMS.getValue(), found_items);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), products.getNumber() + 1);
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products.getContent());
        } catch (ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        return new ModelAndView(PagesPathEnum.PRODUCT_LIST_PAGE.getPath(), modelMap);
    }

    @GetMapping("/product")
    public ModelAndView getProduct(@RequestParam int product_id) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        try {
            Product product = productService.read(product_id);
            modelMap.addAttribute(PRODUCT.getValue(), product);
        } catch (ServiceException e) {
            log.error(e);
        }
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }

    @PostMapping("/products")
    public ModelAndView addProducts(@RequestParam String category_tag,
                                    @RequestParam int product_id,
                                    @RequestParam(required = false) Integer page_items,
                                    @RequestParam(required = false) Integer page_number,
                                    @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        try {
            Category category = categoryService.getCategoryForProducts(category_tag);
            long found_items = productService.countAllProductsByCategory(category);
            
            if (page_items == null) page_items = 5;
            if (page_number == null) page_number = 1;
            if (page_items * page_number > found_items) page_number = (int) found_items / page_items;
            Page<Product> products = productService.getProductsByCategoryNotNull(category, PageRequest.of(page_number - 1, page_items));
            
            Product product = productService.read(product_id);
            orderService.addProductToBasket(order, product);

            modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
            modelMap.addAttribute(FOUND_ITEMS.getValue(), found_items);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), products.getNumber() + 1);
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products.getContent());
        } catch (ServiceException e) {
            log.error(e);
        }


        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(ORDER.getValue(), order);
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        return new ModelAndView(PagesPathEnum.PRODUCT_LIST_PAGE.getPath(), modelMap);
    }

    @PostMapping("/product")
    public ModelAndView addProduct(@RequestParam int product_id,
                                   @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        try {
            Product product = productService.read(product_id);
            orderService.addProductToBasket(order, product);
            modelMap.addAttribute(PRODUCT.getValue(), product);
        } catch (ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(ORDER.getValue(), order);
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }
}
