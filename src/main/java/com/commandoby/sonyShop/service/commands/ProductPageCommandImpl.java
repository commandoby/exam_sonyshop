package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.Basket;
import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.commandoby.sonyShop.service.enums.PagesPathEnum.PRODUCT_PAGE;
import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class ProductPageCommandImpl implements BaseCommand{
    Logger log = Logger.getLogger(getClass());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String productName = servletRequest.getParameter(PRODUCT_NAME.getValue());
        try {
            Product product = getProduct(servletRequest, productName);
            servletRequest.setAttribute(PRODUCT.getValue(), product);
            if (servletRequest.getParameter(PRODUCT_NAME_OUT_OF_PRODUCT.getValue()) != null) {
                addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PRODUCT_PAGE.getPath();
    }

    private Product getProduct(HttpServletRequest servletRequest, String name) throws NoFoundException {
        for (Product p : ShopContent.getProductList()) {
            if (p.getName().equals(name)) return p;
        }
        throw new NoFoundException("Product: " + name + " not found.");
    }

    private void addProductToBasket(HttpServletRequest servletRequest, Product product) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        Basket basket = (Basket) session.getAttribute(BASKET.getValue());
        if (basket == null) basket = new Basket();
        basket.addProduct(product);
        session.setAttribute(BASKET.getValue(), basket);
    }
}
