package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ControllerException;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@RestController
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class OrderController {

    private final Logger log = LogManager.getLogger(OrderController.class);
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public ModelAndView getCart(@ModelAttribute("order") Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (order == null) modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @PostMapping("/cart")
    public ModelAndView getCartAndRemoveProduct(@RequestParam int id,
                                                  @ModelAttribute("order") Order order) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (order == null) order = new Order();

        try {
            orderService.removeProductFromCartByNumber(order, id);
        } catch (NotFoundException | ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(ORDER.getValue(), order);

        return new ModelAndView(PagesPathEnum.CART_PAGE.getPath(), modelMap);
    }

    @GetMapping("/pay")
    public ModelAndView pay(@ModelAttribute("order") Order order,
                            @ModelAttribute("user") User user) throws ControllerException {
        ModelMap modelMap = new ModelMap();

        if (order.getProductList().size() != 0 && user != null) {
            try {
                orderService.orderPayMethod(user, order);
                userService.userPayMethod(user, order);
                modelMap.addAttribute(USER.getValue(), user);
                log.info("Purchased " + order.getProductList().size() + " products.");

                modelMap.addAttribute(CART_SIZE.getValue(), order.getProductList().size());
                modelMap.addAttribute(CART_PRICE.getValue(), order.getOrderPrice());
                modelMap.addAttribute(ORDER.getValue(), new Order());
            } catch (ServiceException e) {
                log.error(e);
            }
        }
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
