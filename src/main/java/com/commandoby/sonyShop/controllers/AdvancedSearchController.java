package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.UseBasketImpl;
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

    private final ProductService productService;
    private final UseBasketImpl useBasketImpl;
    private final CategoryService categoryService;

    public AdvancedSearchController(ProductService productService, UseBasketImpl useBasketImpl,
                                    CategoryService categoryService) {
        this.productService = productService;
        this.useBasketImpl = useBasketImpl;
        this.categoryService = categoryService;
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
        List<Product> products = new ArrayList<>();

        Map<String, Optional<String>> paramsStringMap = new HashMap<>();
        paramsStringMap.put(SEARCH_VALUE.getValue(), Optional.ofNullable(search_value));
        paramsStringMap.put(CATEGORY_TAG.getValue(), Optional.ofNullable(category_tag));
        paramsStringMap.put(SEARCH_COMPARING.getValue(), Optional.ofNullable(search_comparing));
        paramsStringMap.put(IS_QUANTITY.getValue(), Optional.ofNullable(is_quantity));
        productService.defaultParamsStringMap(paramsStringMap);

        Map<String, Optional<Integer>> paramsIntegerMap = new HashMap<>();
        paramsIntegerMap.put(MIN_PRICE.getValue(), Optional.ofNullable(min_price));
        paramsIntegerMap.put(MAX_PRICE.getValue(), Optional.ofNullable(max_price));
        paramsIntegerMap.put(PAGE_ITEMS.getValue(), Optional.ofNullable(page_items));
        paramsIntegerMap.put(PAGE_NUMBER.getValue(), Optional.ofNullable(page_number));
        productService.defaultParamsIntegerMap(paramsIntegerMap);

        if (product_id != null) useBasketImpl.addProductToBasket(order, product_id);

        if (!search_value.equals("") || min_price != null || max_price != null) {
            products = productService.getSearchProductsByParams(paramsStringMap, paramsIntegerMap);
        } else {
            modelMap.addAttribute(INFO.getValue(), "Enter search parameters.");
        }

        productService.prePagination(modelMap, products,
                paramsIntegerMap.get(PAGE_ITEMS.getValue()).get(),
                paramsIntegerMap.get(PAGE_NUMBER.getValue()).get());

        modelMap.addAttribute(SEARCH_VALUE.getValue(), paramsStringMap.get(SEARCH_VALUE.getValue()).get());
        modelMap.addAttribute(CATEGORY_TAG.getValue(), paramsStringMap.get(CATEGORY_TAG.getValue()).get());
        modelMap.addAttribute(SEARCH_COMPARING.getValue(), paramsStringMap.get(SEARCH_COMPARING.getValue()).get());
        modelMap.addAttribute(IS_QUANTITY.getValue(), paramsStringMap.get(IS_QUANTITY.getValue()).get());
        modelMap.addAttribute(CATEGORIES.getValue(), categoryService.getCategories());
        modelMap.addAttribute(PAGE_ITEMS.getValue(), paramsIntegerMap.get(PAGE_ITEMS.getValue()).get());
        modelMap.addAttribute(MIN_PRICE.getValue(), paramsIntegerMap.get(MIN_PRICE.getValue()).orElse(null));
        modelMap.addAttribute(MAX_PRICE.getValue(), paramsIntegerMap.get(MAX_PRICE.getValue()).orElse(null));
        modelMap.addAttribute(PRODUCT_SIZE.getValue(), products.size());
        if (!paramsStringMap.get(CATEGORY_TAG.getValue()).get().equals("")) {
            Category category = categoryService.getCategoryByTag(paramsStringMap.get(CATEGORY_TAG.getValue()).get());
            modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        }

        return new ModelAndView(PagesPathEnum.ADVANCED_SEARCH.getPath(), modelMap);
    }
}
