package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class PayPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int paySize = 0;
        int payPrice = 0;
        Order order = getOrderList(servletRequest);

        paySize = order.getProductList().size();
        payPrice = order.getOrderPrice();

        servletRequest.setAttribute(BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(BASKET_PRICE.getValue(), payPrice);

        if (paySize != 0) {
            try {
                User user = UserPageCommandImpl.getUser(servletRequest);
                user.setBalance(user.getBalance() - payPrice);
                user.addOrder(order);
                log.info("Purchased " + paySize + " products.");
            } catch (NoFoundException e) {
                log.error(e);
            }
        }

        return PagesPathEnum.PAY_PAGE.getPath();
    }

    private Order getOrderList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        order.setDate(LocalDate.now().toString());
        session.setAttribute(ORDER.getValue(), new Order());
        return order;
    }
}
