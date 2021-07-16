package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.controllers.search.AdvancedSearch;
import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UseBasket;
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
    private final AdvancedSearch advancedSearch;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UseBasket useBasket;

    public AdvancedSearchController(AdvancedSearch advancedSearch, ProductService productService,
                                    CategoryService categoryService, UseBasket useBasket) {
        this.advancedSearch = advancedSearch;
        this.productService = productService;
        this.categoryService = categoryService;
        this.useBasket = useBasket;
    }

    @GetMapping("/search")
    public ModelAndView getSearchProducts(@RequestParam(required = false) Integer product_id,
                                          @RequestParam(required = false) String search_value,
                                          @RequestParam(required = false) String category_tag,
                                          @RequestParam(required = false) String search_comparing,
                                          @RequestParam(required = false) Integer min_price,
                                          @RequestParam(required = false) Integer max_price,
                                          @RequestParam(required = false) Integer page_items,
                                          @RequestParam(required = false) Integer page_number,
                                          @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        List<Category> categories = getCategories();
        Category category = getCategory(category_tag);
        List<Product> products = new ArrayList<>();

        if (product_id != null) useBasket.addProductToBasket(order, product_id);

        if (search_value == null) search_value = "";
        if (category_tag == null) category_tag = "";
        if (search_comparing == null || search_comparing.equals("")) search_comparing = "Price+";
        if (!search_value.equals("") || min_price != null || max_price != null) {
            products = advancedSearch.search(search_value, min_price, max_price,
                    category_tag, search_comparing);
        }

        if (page_items == null) page_items = 0;
        if (page_number == null) page_number = 1;
        if (!page_items.equals(0) && !products.isEmpty()) {
            try {
                int pages = (int) Math.ceil(products.size() / page_items.doubleValue());
                if (page_number > pages) page_number = pages;
                List<Product> productPageList = getProductPageList(products, page_items, page_number);
                modelMap.addAttribute(PAGE_MAX.getValue(), pages);
                modelMap.addAttribute(PRODUCT_LIST.getValue(), productPageList/*productPagesList.get(page_number - 1)*/);
            } catch (NumberFormatException e) {
                log.error(e);
            }
            modelMap.addAttribute(PAGE_NUMBER.getValue(), page_number);
        } else {
            modelMap.addAttribute(PRODUCT_LIST.getValue(), products);
            modelMap.addAttribute(PAGE_NUMBER.getValue(), "1");
        }

        modelMap.addAttribute(SEARCH_VALUE.getValue(), search_value);
        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(SEARCH_COMPARING.getValue(), search_comparing);
        modelMap.addAttribute(CATEGORIES.getValue(), categories);
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        modelMap.addAttribute(MIN_PRICE.getValue(), min_price);
        modelMap.addAttribute(MAX_PRICE.getValue(), max_price);
        modelMap.addAttribute(PRODUCT_SIZE.getValue(), products.size());
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());

        return new ModelAndView(PagesPathEnum.ADVANCED_SEARCH.getPath(), modelMap);
    }

    private List<Product> getProductPageList(List<Product> products, int pageItems, int pageNumber) {
        List<Product> newProductList = new ArrayList<>();
        products.stream()
                .skip((long) pageItems * (pageNumber - 1))
                .limit(pageItems)
                .forEach(newProductList::add);
        return newProductList;
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

    private Category getCategory(String categoryTag) {
        Category category = null;
        try {
            category = categoryService.getCategoryByTag(categoryTag);
        } catch (ServiceException e) {
            log.warn(e);
        }
        return category;
    }
}
