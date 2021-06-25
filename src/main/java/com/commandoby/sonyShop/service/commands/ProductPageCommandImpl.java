package com.commandoby.sonyShop.service.commands;

import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.service.enums.PagesPathEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.commandoby.sonyShop.service.enums.RequestParamEnum.*;

public class ProductPageCommandImpl implements BaseCommand{
    Logger log = Logger.getLogger(getClass());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String productName = servletRequest.getParameter(PRODUCT_NAME.getValue());
        try {
            Product product = ShopContent.getProduct(productName);
            servletRequest.setAttribute(PRODUCT.getValue(), product);
            if (servletRequest.getParameter(PRODUCT_NAME_OUT_OF_PRODUCT.getValue()) != null) {
                BasketPageCommandImpl.addProductToBasket(servletRequest, product);
            }
        } catch (NoFoundException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}
