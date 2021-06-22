package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.Basket;
import com.commandoby.sonyShop.exceptions.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.service.enums.PagesPathEnum.PAY_PAGE;
import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class PayCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int paySize = 0;
        int payPrice = 0;
        Basket basket = getBasketList(servletRequest);

        paySize = basket.getProductList().size();
        payPrice = basket.getBasketPrice();

        servletRequest.setAttribute(BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(BASKET_PRICE.getValue(), payPrice);

        log.info("Purchased " + paySize + " products.");

        return PAY_PAGE.getPath();
    }

    private Basket getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        Basket basket = (Basket) session.getAttribute(BASKET.getValue());
        session.setAttribute(BASKET.getValue(), new Basket());
        return basket;
    }
}
