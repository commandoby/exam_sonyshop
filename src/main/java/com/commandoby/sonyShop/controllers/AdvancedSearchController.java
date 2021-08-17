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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes("order")
public class AdvancedSearchController {

    private final Logger log = LogManager.getLogger(AdvancedSearchController.class);
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public AdvancedSearchController(ProductService productService,
                                    CategoryService categoryService, OrderService orderService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/search")
    public ModelAndView getSearchProducts(@RequestParam(required = false) Integer product_id,
                                          @RequestParam(required = false) String search_value,
                                          @RequestParam(required = false) String category_tag,
                                          @RequestParam(required = false) String search_comparing,
                                          @RequestParam(required = false) String is_quantity,
                                          @RequestParam(required = false) Integer min_price,
                                          @RequestParam(required = false) Integer max_price,
                                          @RequestParam(required = false) Integer page_items,
                                          @RequestParam(required = false) Integer page_number,
                                          @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        Map<String, String> paramsStringMap = new HashMap<>();
        paramsStringMap.put(SEARCH_VALUE.getValue(), search_value);
        paramsStringMap.put(CATEGORY_TAG.getValue(), category_tag);
        paramsStringMap.put(SEARCH_COMPARING.getValue(), search_comparing);
        paramsStringMap.put(IS_QUANTITY.getValue(), is_quantity);
        productService.defaultParamsStringMap(paramsStringMap);

        Map<String, Integer> paramsIntegerMap = new HashMap<>();
        paramsIntegerMap.put(MIN_PRICE.getValue(), min_price);
        paramsIntegerMap.put(MAX_PRICE.getValue(), max_price);
        paramsIntegerMap.put(PAGE_ITEMS.getValue(), page_items);
        paramsIntegerMap.put(PAGE_NUMBER.getValue(), page_number);
        productService.defaultParamsIntegerMap(paramsIntegerMap);

        List<Product> products = new ArrayList<>();
        try {
            if (product_id != null) orderService.addProductToBasket(order, product_id);

            if (!paramsStringMap.get(SEARCH_VALUE.getValue()).equals("")
                    || paramsIntegerMap.get(MIN_PRICE.getValue()) != null
                    || paramsIntegerMap.get(MAX_PRICE.getValue()) != null) {
                products = productService.getSearchProductsByParams(paramsStringMap, paramsIntegerMap);
            } else {
                modelMap.addAttribute(INFO.getValue(), "Enter search parameters.");
            }

            productService.prePagination(modelMap, products,
                    paramsIntegerMap.get(PAGE_ITEMS.getValue()),
                    paramsIntegerMap.get(PAGE_NUMBER.getValue()));

            modelMap.addAttribute(PRODUCT_SIZE.getValue(), products.size());
        } catch (ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(SEARCH_VALUE.getValue(), paramsStringMap.get(SEARCH_VALUE.getValue()));
        modelMap.addAttribute(CATEGORY_TAG.getValue(), paramsStringMap.get(CATEGORY_TAG.getValue()));
        modelMap.addAttribute(SEARCH_COMPARING.getValue(), paramsStringMap.get(SEARCH_COMPARING.getValue()));
        modelMap.addAttribute(IS_QUANTITY.getValue(), paramsStringMap.get(IS_QUANTITY.getValue()));
        modelMap.addAttribute(CATEGORIES.getValue(), categoryService.getCategories());
        modelMap.addAttribute(PAGE_ITEMS.getValue(), paramsIntegerMap.get(PAGE_ITEMS.getValue()));
        modelMap.addAttribute(MIN_PRICE.getValue(), paramsIntegerMap.get(MIN_PRICE.getValue()));
        modelMap.addAttribute(MAX_PRICE.getValue(), paramsIntegerMap.get(MAX_PRICE.getValue()));
        if (!paramsStringMap.get(CATEGORY_TAG.getValue()).equals("")) {
            Category category = categoryService.getCategoryByTag(paramsStringMap.get(CATEGORY_TAG.getValue()));
            modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        }

        return new ModelAndView(PagesPathEnum.ADVANCED_SEARCH.getPath(), modelMap);
    }
}
