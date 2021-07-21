package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.UseBasket;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@RestController
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class OrderController {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final UserService userService;
    private final OrderService orderService;
    private final UseBasket useBasket;

    public OrderController(UserService userService, OrderService orderService,
                           UseBasket useBasket) {
        this.userService = userService;
        this.orderService = orderService;
        this.useBasket = useBasket;
    }

    @GetMapping("/basket")
    public ModelAndView getBasket(@ModelAttribute("order") Order order) throws CommandException {
        ModelMap modelMap = new ModelMap();

        if (order == null) modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView(PagesPathEnum.BASKET_PAGE.getPath(), modelMap);
    }

    @PostMapping("/basket")
    public ModelAndView getBasketAndRemoveProduct(@RequestParam int id,
                                                  @ModelAttribute("order") Order order) throws CommandException {
        ModelMap modelMap = new ModelMap();
        if (order == null) order = new Order();
        try {
            useBasket.removeProductWithOfBasket(order, id);
        } catch (NoFoundException | ServiceException e) {
            log.error(e);
        }

        modelMap.addAttribute(ORDER.getValue(), order);

        return new ModelAndView(PagesPathEnum.BASKET_PAGE.getPath(), modelMap);
    }

    @GetMapping("/pay")
    public ModelAndView pay(@ModelAttribute("order") Order order,
                            @ModelAttribute("user") User user) throws CommandException {
        ModelMap modelMap = new ModelMap();
        int paySize = order.getProductList().size();
        int payPrice = order.getOrderPrice();

        if (paySize != 0 && user != null) {
            try {
                order.setDate(LocalDate.now().toString());
                order.setUser(user);
                orderService.create(order);
                user.setBalance(user.getBalance() - payPrice);
                user.addOrder(order);
                userService.update(user);
                modelMap.addAttribute(USER.getValue(), user);
                log.info("Purchased " + paySize + " products.");
            } catch (ServiceException e) {
                log.error(e);
            }
        }

        modelMap.addAttribute(BASKET_SIZE.getValue(), paySize);
        modelMap.addAttribute(BASKET_PRICE.getValue(), payPrice);
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
