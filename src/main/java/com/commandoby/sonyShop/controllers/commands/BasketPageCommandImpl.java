package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class BasketPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int basketPrice = 0;
        int basketSize = 0;
        int userBalance = 0;
        int removeProduct;
        Order order;

        if (servletRequest.getParameter(REMOVE_PRODUCT_ID.getValue()) != null) {
            removeProduct = Integer.parseInt(servletRequest
                    .getParameter(REMOVE_PRODUCT_ID.getValue()));
            order = getBasketList(servletRequest, removeProduct);
        } else {
            order = getBasketList(servletRequest);
        }

        basketPrice = order.getOrderPrice();
        basketSize = order.getProductList().size();

        try {
            userBalance = UserPageCommandImpl.getUser(servletRequest).getBalance();
            servletRequest.setAttribute(USER_BALANCE.getValue(), userBalance);
        } catch (NoFoundException e) {
            log.error(e);
        }

        servletRequest.setAttribute(BASKET_PRICE.getValue(), basketPrice);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(ORDER.getValue(), order);

        return PagesPathEnum.BASKET_PAGE.getPath();
    }

    private static Order getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        if (order == null) order = new Order();
        return order;
    }

    private Order getBasketList(HttpServletRequest servletRequest, int id) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        try {
            order.removeProduct(id);
        } catch (NoFoundException e) {
            log.error(e);
        }
        session.setAttribute(ORDER.getValue(), order);
        return order;
    }

    public static int getBasketSize(HttpServletRequest servletRequest) {
        Order order = getBasketList(servletRequest);
        return order.getProductList().size();
    }

    public static void addProductToBasket(HttpServletRequest servletRequest, Product product) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        if (order == null) order = new Order();
        order.addProduct(product);
        session.setAttribute(ORDER.getValue(), order);
    }
}
