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

        Map<String, String> psm = new HashMap<>();
        psm.put(SEARCH_VALUE.getValue(), search_value);
        psm.put(CATEGORY_TAG.getValue(), category_tag);
        psm.put(SEARCH_COMPARING.getValue(), search_comparing);
        psm.put(IS_QUANTITY.getValue(), is_quantity);
        productService.defaultParamsStringMap(psm);

        Map<String, Integer> pim = new HashMap<>();
        pim.put(MIN_PRICE.getValue(), min_price);
        pim.put(MAX_PRICE.getValue(), max_price);
        pim.put(PAGE_ITEMS.getValue(), page_items);
        pim.put(PAGE_NUMBER.getValue(), page_number);
        productService.defaultParamsIntegerMap(pim);

        Page<Product> products = null;
        try {
            if (product_id != null) {
                Product product = productService.read(product_id);
                orderService.addProductToBasket(order, product);
            }

            if (!psm.get(SEARCH_VALUE.getValue()).equals("")
                    || pim.get(MIN_PRICE.getValue()) != null
                    || pim.get(MAX_PRICE.getValue()) != null) {
                products = productService.getSearchProductsByParams(psm, pim);
                
                if (pim.get(PAGE_ITEMS.getValue()) * (pim.get(PAGE_NUMBER.getValue()) - 1) >= products.getTotalElements()) {
                	pim.put(PAGE_NUMBER.getValue(), (int) Math.ceil(products.getTotalElements() / (float) pim.get(PAGE_ITEMS.getValue())));
                    products = productService.getSearchProductsByParams(psm, pim);
                }

                modelMap.addAttribute(PRODUCT_LIST.getValue(), products.getContent());
                modelMap.addAttribute(FOUND_ITEMS.getValue(), products.getTotalElements());
            } else {
                modelMap.addAttribute(INFO.getValue(), "Enter search parameters.");
            }
        } catch (ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(SEARCH_VALUE.getValue(), psm.get(SEARCH_VALUE.getValue()));
        modelMap.addAttribute(CATEGORY_TAG.getValue(), psm.get(CATEGORY_TAG.getValue()));
        modelMap.addAttribute(SEARCH_COMPARING.getValue(), psm.get(SEARCH_COMPARING.getValue()));
        modelMap.addAttribute(IS_QUANTITY.getValue(), psm.get(IS_QUANTITY.getValue()));
        modelMap.addAttribute(CATEGORIES.getValue(), categoryService.getCategories());
        modelMap.addAttribute(PAGE_ITEMS.getValue(), pim.get(PAGE_ITEMS.getValue()));
        modelMap.addAttribute(PAGE_NUMBER.getValue(), pim.get(PAGE_NUMBER.getValue()));
        modelMap.addAttribute(MIN_PRICE.getValue(), pim.get(MIN_PRICE.getValue()));
        modelMap.addAttribute(MAX_PRICE.getValue(), pim.get(MAX_PRICE.getValue()));
        if (!psm.get(CATEGORY_TAG.getValue()).equals("")) {
            Category category = categoryService.getCategoryByTag(psm.get(CATEGORY_TAG.getValue()));
            modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        }

        return new ModelAndView(PagesPathEnum.ADVANCED_SEARCH.getPath(), modelMap);
    }
}
