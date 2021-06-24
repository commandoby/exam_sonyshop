package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.Order;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class PayPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int paySize = 0;
        int payPrice = 0;
        Order order = getBasketList(servletRequest);

        paySize = order.getProductList().size();
        payPrice = order.getOrderPrice();

        servletRequest.setAttribute(BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(BASKET_PRICE.getValue(), payPrice);

        if (paySize != 0) {
            try {
                User user = UserPageCommandImpl.getUser(servletRequest);
                user.updateBalance(-1 * payPrice);
                user.addOrder(order);
                log.info("Purchased " + paySize + " products.");
            } catch (NoFoundException e) {
                log.error(e);
            }
        }

        return PagesPathEnum.PAY_PAGE.getPath();
    }

    private Order getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        session.setAttribute(ORDER.getValue(), new Order());
        return order;
    }
}
