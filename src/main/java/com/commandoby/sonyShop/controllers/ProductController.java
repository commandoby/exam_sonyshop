package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.repository.domain.Category;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.service.impl.CategoryMethodsImpl;
import com.commandoby.sonyShop.service.impl.ProductMethodsImpl;
import com.commandoby.sonyShop.service.impl.UseBasketImpl;
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

    private final UseBasketImpl useBasketImpl;
    private final CategoryMethodsImpl categoryMethods;
    private final ProductMethodsImpl productMethods;

    public ProductController(UseBasketImpl useBasket, CategoryMethodsImpl categoryMethods,
                             ProductMethodsImpl productMethods) {
        this.useBasketImpl = useBasket;
        this.categoryMethods = categoryMethods;
        this.productMethods = productMethods;
    }

    @GetMapping("/products")
    public ModelAndView getProductsByCategory(@RequestParam String category_tag,
                                              @RequestParam(required = false) Integer page_items,
                                              @RequestParam(required = false) Integer page_number) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Category category = categoryMethods.getCategoryForProducts(category_tag);
        List<Product> products = productMethods.getProductsByCategoryAndQuantityNotNull(category);

        if (page_items == null) page_items = 0;
        if (page_number == null) page_number = 1;
        productMethods.prePagination(modelMap, products, page_items, page_number);

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        return new ModelAndView(PagesPathEnum.PRODUCT_LIST_PAGE.getPath(), modelMap);
    }

    @GetMapping("/product")
    public ModelAndView getProduct(@RequestParam int product_id) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Product product = productMethods.getProductById(product_id);

        modelMap.addAttribute(PRODUCT.getValue(), product);
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }

    @PostMapping("/products")
    public ModelAndView addProducts(@RequestParam String category_tag,
                                    @RequestParam int product_id,
                                    @RequestParam(required = false) Integer page_items,
                                    @RequestParam(required = false) Integer page_number,
                                    @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Category category = categoryMethods.getCategory(category_tag);
        List<Product> products = productMethods.getProductsByCategoryAndQuantityNotNull(category);

        if (page_items == null) page_items = 0;
        if (page_number == null) page_number = 1;
        useBasketImpl.addProductToBasket(order, product_id);
        productMethods.prePagination(modelMap, products, page_items, page_number);

        modelMap.addAttribute(CATEGORY_TAG.getValue(), category_tag);
        modelMap.addAttribute(ORDER.getValue(), order);
        modelMap.addAttribute(PAGE_ITEMS.getValue(), page_items);
        if (category != null) modelMap.addAttribute(CATEGORY_NAME.getValue(), category.getName());
        return new ModelAndView(PagesPathEnum.PRODUCT_LIST_PAGE.getPath(), modelMap);
    }

    @PostMapping("/product")
    public ModelAndView addProduct(@RequestParam int product_id,
                                   @ModelAttribute Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        Product product = useBasketImpl.addProductToBasket(order, product_id);

        modelMap.addAttribute(ORDER.getValue(), order);
        modelMap.addAttribute(PRODUCT.getValue(), product);
        return new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath(), modelMap);
    }
}
