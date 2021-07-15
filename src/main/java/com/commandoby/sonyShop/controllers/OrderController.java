package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.controllers.commands.BaseCommand;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

@Controller
@RequestMapping("/sonyshop")
@SessionAttributes({"user", "order"})
public class OrderController {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/basket")
    public ModelAndView getBasket(@ModelAttribute Order order) throws CommandException {
        ModelMap modelMap = new ModelMap();

        if (order == null) modelMap.addAttribute(ORDER.getValue(), new Order());

        return new ModelAndView("basket", modelMap);
    }

    @PostMapping("/basket")
    public ModelAndView getBasketAndRemoveProduct(@RequestParam int id,
                                                  @ModelAttribute Order order) throws CommandException {
        ModelMap modelMap = new ModelMap();
        if (order == null) order = new Order();
            try {
            removeProduct(order, id);
        } catch (NoFoundException e) {
            log.error(e);
        }

        modelMap.addAttribute(ORDER.getValue(), order);

        return new ModelAndView("basket", modelMap);
    }

    @GetMapping("/pay")
    public ModelAndView pay(@ModelAttribute Order order,
                            @ModelAttribute User user) throws CommandException {
        ModelMap modelMap = new ModelMap();
        int paySize = order.getProductList().size();
        int payPrice = order.getOrderPrice();

        if (paySize != 0 && user != null) {
            try {
                order.setUser(user);
//                orderService.create(order);
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

        return new ModelAndView("pay", modelMap);
    }

    private void removeProduct(Order order, int id) throws NoFoundException {
        if (order.getProductList().get(id) != null) {
            order.getProductList().remove(id);
            order.setOrderPrice(order
                    .getProductList()
                    .stream()
                    .mapToInt(productOrder -> productOrder.getPrice())
                    .sum());
        } else {
            throw new NoFoundException("Will not find a product to remove by id: " + id);
        }
    }
}
