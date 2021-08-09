package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.impl.PayMethodsImpl;
import com.commandoby.sonyShop.service.impl.UseBasketImpl;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@RestController
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class OrderController {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final UseBasketImpl useBasket;
    private final PayMethodsImpl payMethods;

    public OrderController(UseBasketImpl useBasket, PayMethodsImpl payMethods) {
        this.useBasket = useBasket;
        this.payMethods = payMethods;
    }

    @GetMapping("/basket")
    public ModelAndView getBasket(@ModelAttribute("order") Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (order == null) modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView(PagesPathEnum.BASKET_PAGE.getPath(), modelMap);
    }

    @PostMapping("/basket")
    public ModelAndView getBasketAndRemoveProduct(@RequestParam int id,
                                                  @ModelAttribute("order") Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();
        if (order == null) order = new Order();
        try {
            useBasket.removeProductWithOfBasketByNumber(order, id);
        } catch (NotFoundException | ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(ORDER.getValue(), order);

        return new ModelAndView(PagesPathEnum.BASKET_PAGE.getPath(), modelMap);
    }

    @GetMapping("/pay")
    public ModelAndView pay(@ModelAttribute("order") Order order,
                            @ModelAttribute("user") User user) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (order.getProductList().size() != 0 && user != null) {
            try {
                payMethods.orderPayMethod(user, order);
                payMethods.userPayMethod(user, order);
                modelMap.addAttribute(USER.getValue(), user);
                log.info("Purchased " + order.getProductList().size() + " products.");
            } catch (ServiceException e) {
                log.error(e);
            }
        }

        modelMap.addAttribute(BASKET_SIZE.getValue(), order.getProductList().size());
        modelMap.addAttribute(BASKET_PRICE.getValue(), order.getOrderPrice());
        modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView(PagesPathEnum.PAY_PAGE.getPath(), modelMap);
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @ModelAttribute("order")
    public Order getOrder() {
        return new Order();
    }
}
