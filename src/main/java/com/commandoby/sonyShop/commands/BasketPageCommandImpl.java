package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Basket;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.enums.PagesPathEnum.BASKET_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

public class BasketPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int basketPrice = 0;
        int basketSize = 0;
        int removeProduct;
        Basket basket;

        if (servletRequest.getParameter(REMOVE_PRODUCT_ID.getValue()) != null) {
            removeProduct = Integer.parseInt(servletRequest
                    .getParameter(REMOVE_PRODUCT_ID.getValue()));
            basket = getBasketList(servletRequest, removeProduct);
        } else {
            basket = getBasketList(servletRequest);
        }

        basketPrice = basket.getBasketPrice();
        basketSize = basket.getProductList().size();

        servletRequest.setAttribute(BASKET_PRICE.getValue(), basketPrice);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(BASKET.getValue(), basket);

        return BASKET_PAGE.getPath();
    }

    private static Basket getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Basket basket = (Basket) session.getAttribute(BASKET.getValue());
        if (basket == null) basket = new Basket();
        return basket;
    }

    private Basket getBasketList(HttpServletRequest servletRequest, int id) {
        HttpSession session = servletRequest.getSession();
        Basket basket = (Basket) session.getAttribute(BASKET.getValue());
        try {
            basket.removeProduct(id);
        } catch (NoFoundException e) {
            log.error(e);
        }
        session.setAttribute(BASKET.getValue(), basket);
        return basket;
    }

    public static int getBasketSize(HttpServletRequest servletRequest) {
        Basket basket = getBasketList(servletRequest);
        return basket.getProductList().size();
    }
}
