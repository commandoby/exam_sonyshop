package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.CategoryMethodsImpl;
import com.commandoby.sonyShop.service.impl.ProductMethodsImpl;
import com.commandoby.sonyShop.service.impl.UseBasketImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes("order")
public class AdvancedSearchController {

    private final Logger log = Logger.getLogger(getClass());
    private final ProductService productService;
    private final UseBasketImpl useBasketImpl;
    private final CategoryMethodsImpl categoryMethods;
    private final ProductMethodsImpl productMethods;

    public AdvancedSearchController(ProductService productService, UseBasketImpl useBasketImpl,
                                    CategoryMethodsImpl categoryMethods, ProductMethodsImpl productMethods) {
        this.productService = productService;
        this.useBasketImpl = useBasketImpl;
        this.categoryMethods = categoryMethods;
        this.productMethods = productMethods;
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
        Category category = categoryMethods.getCategory(category_tag);
        List<Product> products = new ArrayList<>();

        if (product_id != null) useBasketImpl.addProductToBasket(order, product_id);

        if (search_value == null) search_value = "";
        if (category_tag == null) category_tag = "";
        if (search_comparing == null || search_comparing.equals("")) search_comparing = "Price+";
        if (is_quantity == null) is_quantity = "";
        if (page_items == null) page_items = 0;
        if (page_number == null) page_number = 1;
        if (!search_value.equals("") || min_price != null || max_price != null) {
            products = productService.getSearchProductsByParams(search_value, category_tag, search_comparing,
                    is_quantity, min_price, max_price);
        } else {
            modelMap.addAttribute(INFO.getValue(), "Enter search parameters.");
        }

        if (!page_items.equals(0) && !products.isEmpty()) {
            List<Product> productPageList = productMethods.pagination(modelMap, products, page_items, page_number);
            modelMap.addAttribute(PRODUCT_LIST.getValue(), productPageList);
        } else {
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), "1");
        }

        modelMap.addAttribute(SEARCH_VALUE.getValue(), search_value);
        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(SEARCH_COMPARING.getValue(), search_comparing);
        modelMap.addAttribute(IS_QUANTITY.getValue(), is_quantity);
        modelMap.addAttribute(CATEGORIES.getValue(), categoryMethods.getCategories());
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        modelMap.addAttribute(MIN_PRICE.getValue(), min_price);
        modelMap.addAttribute(MAX_PRICE.getValue(), max_price);
        modelMap.addAttribute(PRODUCT_SIZE.getValue(), products.size());
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());

        return new ModelAndView(PagesPathEnum.ADVANCED_SEARCH.getPath(), modelMap);
    }
}
