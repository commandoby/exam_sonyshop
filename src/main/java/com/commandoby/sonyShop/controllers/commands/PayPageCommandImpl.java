package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class PayPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int paySize = 0;
        int payPrice = 0;
        Order order = getOrder(servletRequest);

        paySize = order.getProductList().size();
        payPrice = order.getOrderPrice();

        servletRequest.setAttribute(BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(BASKET_PRICE.getValue(), payPrice);

        if (paySize != 0) {
            try {
            User user = getUser(servletRequest);
            order.setUser(user);
                user.setBalance(user.getBalance() - payPrice);
                user.addOrder(order);
                log.info("Purchased " + paySize + " products.");
                userService.update(user);
            } catch (ServiceException e) {
                log.error(e);
            }
        }

        return PagesPathEnum.PAY_PAGE.getPath();
    }

    private User getUser(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        String email = (String) session.getAttribute(EMAIL.getValue());
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (ServiceException e) {
            log.error(e);
        }
        return user;
    }

    private Order getOrder(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        order.setDate(LocalDate.now().toString());
        session.setAttribute(ORDER.getValue(), new Order());
        return order;
    }
}
