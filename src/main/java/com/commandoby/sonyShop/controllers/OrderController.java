package com.commandoby.sonyShop.controllers;

import com.commandoby.sonyShop.controllers.commands.BaseCommand;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
