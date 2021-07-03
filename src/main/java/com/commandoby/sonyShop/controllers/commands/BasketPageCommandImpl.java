package com.commandoby.sonyShop.controllers.commands;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.controllers.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.controllers.enums.RequestParamEnum.*;

public class BasketPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int basketPrice = 0;
        int basketSize = 0;
        int userBalance = 0;
        int removeProduct;
        Order order = getBasketList(servletRequest);

        if (servletRequest.getParameter(REMOVE_PRODUCT_ID.getValue()) != null) {
            removeProduct = Integer.parseInt(servletRequest
                    .getParameter(REMOVE_PRODUCT_ID.getValue()));
            try {
                removeProduct(servletRequest, order, removeProduct);
            } catch (NoFoundException e) {
                log.error(e);
            }
        }

        basketPrice = order.getOrderPrice();
        basketSize = order.getProductList().size();

        userBalance = getUserBalance(servletRequest);
        servletRequest.setAttribute(USER_BALANCE.getValue(), userBalance);

        servletRequest.setAttribute(BASKET_PRICE.getValue(), basketPrice);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(ORDER.getValue(), order);

        return PagesPathEnum.BASKET_PAGE.getPath();
    }

    private int getUserBalance(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        String email = (String) session.getAttribute(EMAIL.getValue());
        int userBalance = 0;

        try {
            userBalance = userService.getUserBalanceByEmail(email);
        } catch (ServiceException e) {
            log.error(e);
        }

        return userBalance;
    }

    private static Order getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        if (order == null) order = new Order();
        return order;
    }

    private void removeProduct(HttpServletRequest servletRequest, Order order, int id) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        if (order.getProductList().get(id) != null) {
            order.getProductList().remove(id);
            order.setOrderPrice(order
                    .getProductList()
                    .stream()
                    .mapToInt(productOrder -> productOrder.getPrice())
                    .sum());
            session.setAttribute(ORDER.getValue(), order);
        } else {
            throw new NoFoundException("Will not find a product to remove by id: " + id);
        }
    }

    public static int getBasketSize(HttpServletRequest servletRequest) {
        Order order = getBasketList(servletRequest);
        return order.getProductList().size();
    }

    public static void addProductToBasket(HttpServletRequest servletRequest, Product product) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        Order order = (Order) session.getAttribute(ORDER.getValue());
        if (order == null) order = new Order();
        order.getProductList().add(product);
        order.setOrderPrice(order
                .getProductList()
                .stream()
                .mapToInt(productOrder -> productOrder.getPrice())
                .sum());
        session.setAttribute(ORDER.getValue(), order);
    }
}
